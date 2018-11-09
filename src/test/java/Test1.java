import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.SortedDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.search.join.JoinUtil;
import org.apache.lucene.search.join.ScoreMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Test1 {

    @Test
    public void test(){
        final String department="department";
        final String position="position";
        final String talent="talent";
        try {
            Directory dir = FSDirectory.open(Paths.get("G:\\hr_manager\\position_tb"));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            IndexWriter w = new IndexWriter(dir, config);

            Document doc0 = new Document();
            doc0.add(new TextField("depart_id", "1", Field.Store.YES));
            doc0.add(new TextField("depart_name", "部门一", Field.Store.YES));
            doc0.add(new TextField("depart_phone", "123", Field.Store.YES));
            w.addDocument(doc0);

            Document doc1 = new Document();
            doc1.add(new TextField("depart_id", "2", Field.Store.YES));
            doc1.add(new TextField("depart_name", "部门二", Field.Store.YES));
            doc1.add(new TextField("depart_phone", "1234", Field.Store.YES));
            w.addDocument(doc1);

            Document doc2 = new Document();
            doc2.add(new TextField("depart_id", "3", Field.Store.YES));
            doc2.add(new TextField("depart_name", "部门三", Field.Store.YES));
            doc2.add(new TextField("depart_phone", "12345", Field.Store.YES));
            w.addDocument(doc2);

            Document doc3 = new Document();
            doc3.add(new TextField("depart_id", "4", Field.Store.YES));
            doc3.add(new TextField("depart_name", "部门四", Field.Store.YES));
            doc3.add(new TextField("depart_phone", "123456", Field.Store.YES));
            w.addDocument(doc3);

            Document doc4 = new Document();
            doc4.add(new TextField("depart_id", "5", Field.Store.YES));
            doc4.add(new TextField("depart_name", "部门五", Field.Store.YES));
            doc4.add(new TextField("depart_phone", "1234567", Field.Store.YES));
            w.addDocument(doc4);

            Document doc5 = new Document();
            doc5.add(new TextField("depart_id", "6", Field.Store.YES));
            doc5.add(new TextField("depart_name", "部门六", Field.Store.YES));
            doc5.add(new TextField("depart_phone", "12345678", Field.Store.YES));
            w.addDocument(doc5);

            Document doc6 = new Document();
            doc6.add(new TextField("depart_id", "7", Field.Store.YES));
            doc6.add(new TextField("depart_name", "部门七", Field.Store.YES));
            doc6.add(new TextField("depart_phone", "123456789", Field.Store.YES));
            w.addDocument(doc6);

            Document doc7 = new Document();
            doc7.add(new TextField("depart_id", "8", Field.Store.YES));
            doc7.add(new TextField("depart_name", "部门七", Field.Store.YES));
            doc7.add(new TextField("depart_phone", "1", Field.Store.YES));
            w.addDocument(doc7);

            Document doc8 = new Document();
            doc8.add(new TextField("depart_id", "9", Field.Store.YES));
            doc8.add(new TextField("depart_name", "部门七", Field.Store.YES));
            doc8.add(new TextField("depart_phone", "12", Field.Store.YES));
            w.addDocument(doc8);





            Document doc13 = new Document();
            doc13.add(new TextField("position_id", "1", Field.Store.YES));
            doc13.add(new TextField("position_name", "岗位1", Field.Store.YES));
            doc13.add(new TextField("pos_salary", "12", Field.Store.YES));
            w.addDocument(doc13);

            Document doc9 = new Document();
            doc9.add(new TextField("position_id", "3", Field.Store.YES));
            doc9.add(new TextField("position_name", "岗位3", Field.Store.YES));
            doc9.add(new TextField("pos_salary", "12", Field.Store.YES));
            w.addDocument(doc9);

            Document doc10 = new Document();
            doc10.add(new TextField("position_id", "5", Field.Store.YES));
            doc10.add(new TextField("position_name", "岗位5", Field.Store.YES));
            doc10.add(new TextField("pos_salary", "12", Field.Store.YES));
            w.addDocument(doc10);

            Document doc11 = new Document();
            doc11.add(new TextField("position_id", "7", Field.Store.YES));
            doc11.add(new TextField("position_name", "岗位7", Field.Store.YES));
            doc11.add(new TextField("pos_salary", "12", Field.Store.YES));
            w.addDocument(doc11);

            Document doc12 = new Document();
            doc12.add(new TextField("position_id", "13", Field.Store.YES));
            doc12.add(new TextField("position_name", "岗位13", Field.Store.YES));
            doc12.add(new TextField("pos_salary", "12", Field.Store.YES));
            w.addDocument(doc12);


            Document doc = new Document();
            doc.add(new TextField("talent_id", "1", Field.Store.YES));
            doc.add(new TextField("talent_name", "黄达盛", Field.Store.YES));
            doc.add(new TextField("de_parent", "1", Field.Store.YES));
            doc.add(new SortedDocValuesField("de_parent", new BytesRef("1")));
            w.addDocument(doc);
            w.commit();
            w.close();

            IndexReader reader = DirectoryReader.open(dir);
            IndexSearcher indexSearcher = new IndexSearcher(reader);

            Query joinQuery = JoinUtil.createJoinQuery("de_parent", false, "depart_id", new TermQuery(new Term("talent_name", "黄")), indexSearcher, ScoreMode.None);
            System.out.println(joinQuery);
            TopDocs topDocs = indexSearcher.search(joinQuery, 10);

            System.out.println("查询到的匹配数据："+topDocs.totalHits);
            ScoreDoc[] s =topDocs.scoreDocs;
            for (int i=0;i<s.length;i++){
                int id=s[i].doc;
                System.out.println(id);
                Document document = indexSearcher.doc(id);
                List<IndexableField> fields = document.getFields();
                for (IndexableField indexableField:fields){
                    System.out.println(indexableField);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSimple() throws Exception {
        final String idField = "id";
        final String toField = "productId";

        Directory dir = FSDirectory.open(Paths.get("G:\\hr_manager\\position_tb"));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter w = new IndexWriter(dir, config);

        // 0
        Document doc = new Document();
        doc.add(new TextField("description", "random text", Field.Store.YES));
        doc.add(new TextField("name", "name1", Field.Store.YES));
        doc.add(new TextField(idField, "1", Field.Store.YES));
        doc.add(new SortedDocValuesField(idField, new BytesRef("1")));

        w.addDocument(doc);

        // 1
        Document doc1 = new Document();
        doc1.add(new TextField("price", "10.0", Field.Store.YES));
        doc1.add(new TextField(idField, "2", Field.Store.YES));
        doc1.add(new SortedDocValuesField(idField, new BytesRef("2")));
        doc1.add(new TextField(toField, "1", Field.Store.YES));
        doc1.add(new SortedDocValuesField(toField, new BytesRef("1")));

        w.addDocument(doc1);

        // 2
        Document doc2 = new Document();
        doc2.add(new TextField("price", "20.0", Field.Store.YES));
        doc2.add(new TextField(idField, "3", Field.Store.YES));
        doc2.add(new SortedDocValuesField(idField, new BytesRef("3")));
        doc2.add(new TextField(toField, "1", Field.Store.YES));
        doc2.add(new SortedDocValuesField(toField, new BytesRef("1")));

        w.addDocument(doc2);

        // 3
        Document doc3 = new Document();
        doc3.add(new TextField("description", "more random text", Field.Store.YES));
        doc3.add(new TextField("name", "name2", Field.Store.YES));
        doc3.add(new TextField(idField, "4", Field.Store.YES));
        doc3.add(new SortedDocValuesField(idField, new BytesRef("4")));

        w.addDocument(doc3);


        // 4
        Document doc4 = new Document();
        doc4.add(new TextField("price", "10.0", Field.Store.YES));
        doc4.add(new TextField(idField, "5", Field.Store.YES));
        doc4.add(new SortedDocValuesField(idField, new BytesRef("5")));
        doc4.add(new TextField(toField, "4", Field.Store.YES));
        doc4.add(new SortedDocValuesField(toField, new BytesRef("4")));
        w.addDocument(doc4);

        // 5
        Document doc5 = new Document();
        doc5.add(new TextField("price", "20.0", Field.Store.YES));
        doc5.add(new TextField(idField, "6", Field.Store.YES));
        doc5.add(new SortedDocValuesField(idField, new BytesRef("6")));
        doc5.add(new TextField(toField, "4", Field.Store.YES));
        doc5.add(new SortedDocValuesField(toField, new BytesRef("4")));
        w.addDocument(doc5);

        //6
        Document doc6 = new Document();
        doc6.add(new TextField(toField, "4", Field.Store.YES));
        doc6.add(new SortedDocValuesField(toField, new BytesRef("4")));
        w.addDocument(doc6);
        w.commit();
        w.close();
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher indexSearcher = new IndexSearcher(reader);


        // Search for product
        Query joinQuery = JoinUtil.createJoinQuery(idField, false, toField, new TermQuery(new Term("name", "name2")), indexSearcher, ScoreMode.None);
        System.out.println(joinQuery);
        TopDocs result = indexSearcher.search(joinQuery, 10);
        System.out.println("查询到的匹配数据："+result.totalHits);


        joinQuery = JoinUtil.createJoinQuery(idField, false, toField, new TermQuery(new Term("name", "name1")), indexSearcher, ScoreMode.None);
        result = indexSearcher.search(joinQuery, 10);
        System.out.println("查询到的匹配数据："+result.totalHits);
        // Search for offer
        joinQuery = JoinUtil.createJoinQuery(toField, false, idField, new TermQuery(new Term("id", "5")), indexSearcher, ScoreMode.None);
        result = indexSearcher.search(joinQuery, 10);
        System.out.println("查询到的匹配数据："+result.totalHits);

        indexSearcher.getIndexReader().close();
        dir.close();
    }


}
