<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 11.03.2021
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
hello, ${username}
you are ${role}
<br>
<c:if test="${not empty requestScope.users}">
    <h2>Users</h2>
    <ul>
        <c:forEach var="user" items="${requestScope.users}">
            <li>
                ${user.login} has password ${user.password}
            </li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
