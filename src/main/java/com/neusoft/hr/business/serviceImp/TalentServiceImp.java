package com.neusoft.hr.business.serviceImp;

import com.neusoft.hr.business.entity.EmlpoyeeTranform;
import com.neusoft.hr.business.entity.Talent;
import com.neusoft.hr.business.responsitory.EmlpoyeeTranformDao;
import com.neusoft.hr.business.responsitory.TalentDao;
import com.neusoft.hr.business.service.TalentService;
import com.neusoft.hr.business.unit.PageBean;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.SortedDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class TalentServiceImp implements TalentService {

    @Autowired
    private TalentDao talentDao;
    @Autowired
    private EmlpoyeeTranformDao emlpoyeeTranformDao;
    IndexWriter indexWriter;

    //修改人才信息
    //
    @Transactional
    public void updateTalent(Talent talent) {
        //查找当前所在部门和岗位
        Talent talent1 = talentDao.queryTalentById(talent.getId());
        //往流转表加入
        Date date=new Date();
        EmlpoyeeTranform emlpoyeeTranform = new EmlpoyeeTranform();
        emlpoyeeTranform.settId(talent.getId());
        emlpoyeeTranform.setInDepartId(talent.getDepartment().getId());
        emlpoyeeTranform.setOutDepartId(talent1.getDepartment().getId());
        emlpoyeeTranform.setInPosId(talent.getPosition().getId());
        emlpoyeeTranform.setOutPosId(talent1.getPosition().getId());
        emlpoyeeTranform.setTranDate(date);

        emlpoyeeTranformDao.insertEm(emlpoyeeTranform);

        //插入索引
        try {
            Directory directory=FSDirectory.open(Paths.get("G:\\hr_manager\\tran_tb"));
            //分词器
            Analyzer analyzer = new SmartChineseAnalyzer();

            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            indexWriter = new IndexWriter(directory,indexWriterConfig);

            Document doc = new Document();
            doc.add(new TextField("talent_name", talent.getName(), Field.Store.YES));
            doc.add(new TextField("out_depart_name", talent1.getDepartment().getDepartName(), Field.Store.YES));
            doc.add(new TextField("out_position_name", talent1.getPosition().getPosName(), Field.Store.YES));
            doc.add(new TextField("tran_date", date.toString(), Field.Store.YES));
            indexWriter.addDocument(doc);
            indexWriter.commit();



        } catch (IOException e) {
            e.printStackTrace();
       }finally {
            try {
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        talentDao.updateTalent(talent);
    }


    @Override
    public PageBean<Talent> queryAllTalentLimit(PageBean<Talent> pageBean, String query) {
        PageBean<Talent> pageBean1 = new PageBean<>();
        //查询总条数
        int total = talentDao.getTotal(query);
        List<Talent> talents = talentDao.queryAllTalent((pageBean.getPage()-1)*pageBean.getLimit(),pageBean.getLimit(), query);
        pageBean1.setTotalCount(total);
        pageBean1.setList(talents);
        pageBean1.setPage(pageBean.getPage());
        pageBean1.setLimit(pageBean.getLimit());
        if(pageBean.getQuery()!=null)
            pageBean1.setQuery(pageBean.getQuery());
        pageBean1.setTotalPage(pageBean.getTotalPage());
        return pageBean1;
    }
}
