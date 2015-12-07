/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crawler;

/**
 *
 * @author N.Yuvanesh
 */
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 
public class test{
	public static DB db = new DB();
        static int count=0;
        static String mainURL="https://www.annauniv.edu/";
        static String keyword=".pdf"; 
        
	public static void main(String[] args) throws SQLException, IOException {
		db.runSql2("TRUNCATE Record;");
                mainsearch(mainURL);
                                            
	}
        public static void mainsearch(String URL)
        {
            
        try{
            
             String sql = "INSERT INTO  `Crawler`.`Record` " + "(`URL`) VALUES " + "(?);";
             PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             
             stmt.setString(1, URL);
             stmt.execute();
            
            
	    Document doc = Jsoup.connect(URL).timeout(0).get(); 
            Elements inputElements = doc.getElementsByTag("a");
            for (Element inputElement : inputElements) {  
                         
                
                String key = inputElement.attr("abs:href");
                String content = inputElement.text();
                ResultSet rs1;
                try{sql = "select * from Record where URL = '"+key+"'";
		rs1 = db.runSql(sql);
                }
                catch(Exception e){continue;}
               	if(rs1.next()){
                
                }
                else
                {
                                sql = "INSERT INTO  `Crawler`.`Record` " + "(`URL`) VALUES " + "(?);";
                                stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                stmt.setString(1, key);
                                stmt.execute();
                                 
                         if(content.toLowerCase().contains(keyword)|| key.toLowerCase().contains(keyword))
                         {
                             
                             System.out.println(key);
                             processPage(key);
                             
                         }
                         
                         
                                               
                
                }
            }
        }
            catch(java.net.SocketTimeoutException e)
            {
                System.out.println(e); 
            }
            catch(Exception e)
                    {
                       System.out.println(e); 
                       e.printStackTrace();
                    }
            
        }
 
      	public static void processPage(String URL) throws SQLException, IOException, Exception{
		
                  try
                        {
                            
                            
                        Document doc = Jsoup.connect(URL).timeout(0).get();
                                   
			  
			                        
		Elements links = doc.select("a[href]");
		for (Element inputElement : links) {  
                         
                String key = inputElement.attr("abs:href");
                String content = inputElement.text();
                String sql = "select * from Record where URL = '"+key+"'";
		ResultSet rs1 = db.runSql(sql);
		if(rs1.next()){
 
		}
                else
                {
                     sql = "INSERT INTO  `Crawler`.`Record` " + "(`URL`) VALUES " + "(?);";
		     PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		     stmt.setString(1, key);
		     stmt.execute();
                                 
                     if((content.toLowerCase().contains(keyword)|| key.toLowerCase().contains(keyword)))
                     { 
                         
                         System.out.println(key);
                         
                     }
                
                }
            
                        }
                   }
                        catch(IOException | SQLException e)
                        {
                            
                        }
                
		
	}
    
}