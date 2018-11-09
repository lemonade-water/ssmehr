import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Test2 {



    @Test
    public void test2(){
        Scanner scanner =new Scanner(System.in);
        Directory directory= null;
        try {
            directory = FSDirectory.open(Paths.get("G:\\hr_manager\\tran_tb"));
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            Analyzer analyzer = new SmartChineseAnalyzer();
            QueryParser queryParser = new MultiFieldQueryParser(new String[]{"out_depart_name","talent_name","out_position_name","tran_date"},analyzer);
            try {
                Query query = queryParser.parse("岗位");
                TopDocs topDocs = indexSearcher.search(query,10);
                ScoreDoc[] scoreDocs = topDocs.scoreDocs;
                System.out.println("数目："+scoreDocs.length);
                for (int i=0;i<scoreDocs.length;i++){
                   int id = scoreDocs[i].doc;
                    Document doc = indexSearcher.doc(id);
                    System.out.println("人员名字:"+doc.get("talent_name"));
                    System.out.println("out岗位:"+doc.get("out_position_name"));
                    System.out.println("out部门:"+doc.get("out_depart_name"));
                    System.out.println("日期:"+doc.get("tran_date"));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }finally {
                indexReader.close();
                directory.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
