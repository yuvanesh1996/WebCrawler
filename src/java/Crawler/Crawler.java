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
import java.net.SocketTimeoutException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 
public class Crawler{
	public static DB db = new DB();
        static int count=0;
      
        static String keyword;
	public Crawler(String keyword1){
                keyword=keyword1;
                try{
		db.runSql2("TRUNCATE Record;");
                db.runSql2("TRUNCATE result;");
                }
                catch(Exception e)
                {
                    
                }                                
	}
       
        public static void mainsearch(String URL)throws Exception
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
                                 
                         if( key.toLowerCase().contains(keyword)|| content.toLowerCase().contains(keyword) )
                         {
                            /* sql="select * from result where Content='"+content+"'";
                             ResultSet rs = db.runSql(sql);
                        	if( !rs.next()) {
                                    } else {
                                    continue;
                                    }
                                 */             
                             sql = "INSERT INTO  `Crawler`.`result` " + "(`URL`,`Content`) VALUES " + "(?,?);";
                                stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                stmt.setString(1, key);
                                 stmt.setString(2, content);
                                stmt.execute();
                             processPage(key);
                             
                         }
                         
                         
                                               
                
                }
            }
        }
            catch(java.net.SocketTimeoutException e)
            {
               throw new SocketTimeoutException();
            }
            catch(Exception e)
                    {
                       System.out.println(e);
                      
                    }
        
         
        }
 
      	public static void processPage(String URL) throws SQLException, IOException{
		
                  try
                        {
                            
                Document doc = Jsoup.connect(URL).timeout(0).get();
                                   
			  
			                        
		Elements links = doc.select("a[href]");
		for (Element inputElement : links) {  
                         
                String key = inputElement.attr("abs:href");
                String content = inputElement.text();
                 String sql;
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
		     PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		     stmt.setString(1, key);
		     stmt.execute();
                                 
                     if((content.toLowerCase().contains(keyword)|| key.toLowerCase().contains(keyword)))
                     { 
                         /*sql="select * from result where Content='"+content+"'";
                             ResultSet rs = db.runSql(sql);
                        	if( !rs.next()) {
                                    } else {
                                    continue;
                                    }*/
                                sql = "INSERT INTO  `Crawler`.`result` " + "(`URL`,`Content`) VALUES " + "(?,?);";
                                stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                stmt.setString(1, key);
                                stmt.setString(2, content);
                                stmt.execute();
                              
                     }
                
                }
            
                        }
                   }
                        catch(Exception e)
                        {
                            
                        }
                  
		 
	}
        
    
}


