
<%-- 
    Document   : query
    Created on : 27 Mar, 2015, 9:58:09 PM
    Author     : N.Yuvanesh
--%>


<%@page import="java.sql.ResultSet"%>
<%@page import="Crawler.DB"%>
<%@page import="java.io.IOException" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <
        <p style="color:#ff0000" >Hello </p>
        <% try{ 
        DB db = new DB();
         String sql = "select * from result ";
         
         ResultSet rs = db.runSql(sql);
         
        %>
         
               
         
        <% while( rs.next() ){
        %>
        
        <% out.println("<br><a href=" + rs.getString(1)+ ">"+"<p style=\"color:#ff0000;font-size:200%;\" >" + rs.getString(1)+"</p></a>"+rs.getString(2));%>
        
        <%}}catch(Exception e){out.println("<h1>"+e+"</h1>"); } %>
        
    </body>
</html>
