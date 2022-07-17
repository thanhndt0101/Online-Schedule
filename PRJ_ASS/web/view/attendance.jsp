<%-- 
    Document   : attendance
    Created on : Jun 30, 2022, 9:27:45 PM
    Author     : HAICAO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance Page</title>
        <link href="css/schedule_csstemplate.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav class="headerrrr">
            <a href="schedule">Home</a>
           
            <c:if test="${sessionScope.account ne null}">
                <a href="logout">Logout</a>
            </c:if>
            <c:if test="${sessionScope.account eq null}">
                <a href="login">Login</a>
            </c:if>
        </nav>
        
        <form action="attend" method="POST">
            <table border="1" style=" margin-top: 20px; width: 70%;">
                <thead class="tbl-header">
                    <tr>
                        <td>No</td>
                        <td>Group</td>
                        <td>Code</td>
                        <td class="long">Name</td>
                        <td class="long">Status</td>
                        <td class="long">Comment</td>
                        <td>Taker</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.enrolls}" var="e">
                        <tr>
                            <td>${enrolls.indexOf(e)+1}</td>
                            <td>${e.group.id}</td>
                            <td>${e.student.code}</td>
                            <td>${e.student.name}</td>
                            <td>
                                <input type="radio" value="false" 
                                       <c:if test="${!e.student.isAttend(session)}">
                                           checked ="checked"
                                       </c:if>
                                       name="check_${e.student.id}">absent

                                <input type="radio" value="true" 
                                       <c:if test="${e.student.isAttend(session)}">
                                           checked="checked"
                                       </c:if>
                                       name="check_${e.student.id}">present

                            </td>
                            <td><input type="text" name="comment_${e.student.id}"></td>
                            <td>${e.group.lecture}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table> <br>
            <input type="submit" value="Save">
        </form>
    </body>
</html>
