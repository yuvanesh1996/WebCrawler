
<%@page import="java.sql.ResultSet"%>
<%-- 
    Document   : search
    Created on : 27 Mar, 2015, 8:11:04 PM
    Author     : N.Yuvanesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Crawler.DB"%>
<%@page import="Crawler.Crawler" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <%!String url=null,key=null;%>
     <style>
         body{
             background-color: lightgoldenrodyellow;
         }
        .tab{
             
            width:102%;
            height:150px;
            padding:0px;
            margin:-10px;
            background-color: lightcyan;
         }  
         .logo{
             width:327px;
             height:72px;
             position:absolute;
             left:40%;
             background-image: url("crawler.png");
         }
         .pos{
             color:black;height:30px;width:500px;
             font-size: 150%;
             position:absolute;
             top:100px;
             left:10px;
         }
          .pos1{
             color:black;height:30px;width:500px;
             font-size: 150%;
             position:absolute;
             top:100px;
             left:700px;
         }
         .linkstyle
         {
            width:102%;
            height:30px;
            padding:0px;
            margin:-10px;
            background-color: lime;
         }
  
         input
         {
 border:1px solid olive;
 border-radius:5px;
 }}
    
     </style>
     <script type="text/javascript">
         
          function urlcheck()
         {
var message;
var myRegExp =/^(?:(?:https?|ftp):\/\/)(?:\S+(?::\S*)?@)?(?:(?!10(?:\.\d{1,3}){3})(?!127(?:\.\d{1,3}){3})(?!169\.254(?:\.\d{1,3}){2})(?!192\.168(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/[^\s]*)?$/i;
var urlToValidate = document.forms.search.url.value;
if (!myRegExp.test(urlToValidate)){
message = "Not a valid URL.";
alert(message);return false;
}
             return true;
         }
                 
         
     </script>
    </head>
    
        
         <% 
          try{
               
          url = request.getParameter("url");
          key= request.getParameter("key");
         }
         catch(Exception e)
         {
             out.println("<p>"+e+"<p>");
         }
         
        %>
        
        <div class="tab">
            <div class="logo"></div>
            
            <form action="search.jsp"  method="post" name="search" onsubmit="return urlcheck()">
            <input class="pos1" name="url" type="text" value="<%= url %>" >
            <input name="key" onKeydown="if(event.keyCode===13){this.form.submit();}" class="pos" type="text" value="<%= key %>" > 
            <p style="font-size:170%;position:absolute;top:80px;left:550px;color:red">@</p> 
                  
            <form>
        
        <div>
        <% try{
            
            Crawler c= new Crawler(key);
            c.mainsearch(url);
                 
         DB db = new DB();
         String sql = "select * from result ";
         
         ResultSet rs = db.runSql(sql);
         
        %>
         
        <br><br><br><br><br><br><br/><br>    
         
        <% while( rs.next() ){
        %>
        
        <style>
            h1{
                margin-left: 20px;
                font-size:200%;
            }
            p{
                margin-left: 20px;
                color:#ff0000;
                font-size:200%;
            }
            
        </style>
        <%
            
        out.print("<h1>"+rs.getString(2)+"</h1>");
        out.print("<a href=" + rs.getString(1)+ ">"+"<p>" + rs.getString(1)+"</p></a><br><br><br><br>");%>        
        <%}}catch(Exception e){out.println("<h1>"+e+"</h1>");}%>
 

    
</html>
