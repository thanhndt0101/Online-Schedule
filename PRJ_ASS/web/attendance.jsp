<%-- 
    Document   : listStudent
    Created on : Jul 12, 2022, 11:03:44 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
    <body>
        <div>Attendance for PRF192 with lecture SonNT5 at slot 1 on Wednesday 19/01/2022</div>
        <table border="1" style="width : 80%;text-align : center">
            <tr>
                <td>no</td>
                <td>group</td>
                <td>code</td>
                <td>name</td>
                <td>image</td>
                <td>status</td>
                <td>comment</td>
                <td>taker</td>

            </tr>
            <tr style="vertical-align: top;">

                <c:forEach items="${listStudent}" var ="student">
                <tr>
                    <td>${student.id}</td>
                    <td></td> 

                    <td>${student.code}</td>
                    <td>${student.name}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td> 


                </tr>
            </c:forEach>


        </tr>





    </table>
</body>
</html>
