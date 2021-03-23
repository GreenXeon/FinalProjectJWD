<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 15.03.2021
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Periodicals subscription</title>
    <link href="${pageContext.request.contextPath}/css/guestPage.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="indexHeader.jsp"/>
<c:if test="${not empty requestScope.periodicals}">
    <div class="periodicals">
        <h2 class="caption">Periodicals</h2>
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
                    <td>${periodical.name}</td>
                    <td>${periodical.author}</td>
                    <td>${periodical.publishDate}</td>
                    <td>${periodical.type}</td>
                    <td>${periodical.subCost}</td>
                    <td>${periodical.publisher}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>
