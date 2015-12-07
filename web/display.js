 /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
authdata();
});

function authdata()
{
var info;
$.ajax({

url: "Details.xml",
dataType: "xml",
success: function(data) {
 $("#123").children().remove();
 
 info="<table border=\"1\"> <tr> <th>Name</th><th>RollNo</th><th>Organisation</th></tr>";
 $(data).find("authour").each(function(){
     
     info+='<tr><td>'+$(this).find("name").text()+'</td><td>'
     + $(this).find("rollno").text()+'</td><td>'
     + $(this).find("org").text()+'</td></tr>';
     
     
     
     
 });
 info+='</table>';
  $("#123").append(info); 
}
});
}




