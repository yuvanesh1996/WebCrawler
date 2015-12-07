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
$.ajax({

url: "Details.xml",
dataType: "xml",
success: function(data) {
 $("ul").children().remove();
 
 $(data).find("authour").each(function(){
     
     var info= '<br><li>Name:&nbsp;&nbsp;'+$(this).find("name").text()+'</li><li>RollNO:&nbsp;&nbsp;'
     + $(this).find("rollno").text()+'</li><li>Organization:&nbsp;&nbsp;'
     + $(this).find("org").text()+'</li>';
     $("ul").append(info);  
     
     
     
 });
 
 
}
});
}




