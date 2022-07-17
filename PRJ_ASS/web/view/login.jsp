<%-- 
    Document   : login
    Created on : Jun 14, 2022, 10:29:45 PM
    Author     : HAICAO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="css/login_csstemplate.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action="login" method="POST" class="login">
            <input type="text" placeholder="Username" name="user"/> <br/>
            <input type="password" placeholder="Password" name="pass"/> <br/>
            <button>Login</button>
        </form>
        
    </body>
</html>
