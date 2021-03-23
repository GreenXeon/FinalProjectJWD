<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 18.03.2021
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All users</title>
    <link href="${pageContext.request.contextPath}/css/showAllUsers.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="adminHeader.jsp"/>
<body>
<c:if test="${not empty requestScope.users}">
    <div class="users">
        <table>
            <caption>Users</caption>
            <tr>
                <th>Login</th>
                <th>Name</th>
                <th>Surname</th>
                <th>E-mail</th>
                <th>Registration date</th>
                <th>Cash</th>
                <th>Role</th>
                <th>Blocked</th>
                <th>Block</th>
                <th>Make admin</th>
            </tr>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <td>${user.login}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.email}</td>
                    <td>${user.registrationDate}</td>
                    <td>${user.cash}</td>
                    <td>${user.role}</td>
                    <td>${user.blocked}</td>
                    <td>
                        <a href="#">Block</a>
                    </td>
                    <td>
                        <a href="#">Make admin</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>
