<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Welcome</title>
      <link rel="stylesheet" type="text/css"
                href="${pageContext.request.contextPath}/css/style.css"/>
   </head>
   <body>
	   <div> ${message} </div>
	   <div>
	   		<form action="/logout" method="post">
	            <input type="submit" value="Sign Out"/>
	        </form>
	   </div>
      
      
    
        
    
      
   </body>
  
</html>