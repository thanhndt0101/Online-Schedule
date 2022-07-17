<%-- 
    Document   : view_sessison_attendance
    Created on : Jul 14, 2022, 8:47:26 PM
    Author     : HAICAO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <a href="schedule">Home</a>
            
            <c:if test="${sessionScope.account ne null}">
                <a href="logout">Logout</a>
            </c:if>
            <c:if test="${sessionScope.account eq null}">
                <a href="login">Login</a>
            </c:if>
        </nav>
        Attendance for with lecture ${session.taker.id} at slot ${session.slot.slot} on ${session.date} <br>
        <form action="view" method="POST">
            <table border="1" style=" margin-top: 20px; width: 70%;">
                <thead>
                    <tr>
                        <td>No</td>
                        <td>Group</td>
                        <td>Code</td>
                        <td>Name</td>
                        <td>Status</td>
                        <td>Comment</td>
                        <td>Taker</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.enrolls}" var="e">
                        <tr>
                            <td>${enrolls.indexOf(e)+1}</td>
                            <td>${e.group.id}</td>
                            <td>${e.student.code}</td>
                            <td>${e.student.name}</td>
                            <td>
                                <c:if test="${!e.student.isAttend(session)}">
                                    absent
                                </c:if>

                                <c:if test="${e.student.isAttend(session)}">
                                    present
                                </c:if>
                            </td>
                            <td>${e.student.getComment(session)}</td>
                            <td>${e.group.lecture}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table> <br>
            <input type="submit" value="Edit">
        </form>
    </body>
</html>
