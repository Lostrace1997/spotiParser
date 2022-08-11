<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> </title>
    </head>
    <body>
 
      	
        <div class="container" style="width: 300px;">
		    <form action="/perform_login" method="post">
		        <h2 class="form-signin-heading">Please sign in</h2>
		        <input type="text" class="form-control" name="p_username" placeholder="Email address" required autofocus value="user">
		        <input type="password" class="form-control" name="p_password" placeholder="Password" required value="user">
		        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
		    </form>
		</div>
    </body>
</html>