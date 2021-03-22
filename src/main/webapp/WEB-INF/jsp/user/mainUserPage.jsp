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
<c:if test="${not empty requestScope.periodicals}">
    <div class="periodicals">
        <table>
            <tr>
                <th>Name</th>
                <th>Author</th>
                <th>Publish date</th>
                <th>Type</th>
                <th>Cost</th>
                <th>Publisher</th>
            </tr>
            <c:forEach var="periodical" items="${requestScope.periodicals}">
                <tr>
                    <th>${periodical.name}</th>
                    <th>${periodical.author}</th>
                    <th>${periodical.publishDate}</th>
                    <th>${periodical.type}</th>
                    <th>${periodical.subCost}</th>
                    <th>${periodical.publisher}</th>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>
