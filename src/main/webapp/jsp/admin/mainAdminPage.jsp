<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 16.03.2021
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>
    <link href="${pageContext.request.contextPath}/css/mainAdminPage.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="adminHeader.jsp"/>
<c:if test="${not empty requestScope.periodicals}">
    <div class="periodicals">
        <table>
            <tr>
                <th>Name</th>
                <th>Author</th>
                <th>Publish year</th>
                <th>Type</th>
                <th>Cost</th>
                <th>Publisher</th>
                <th>Buy</th>
            </tr>
            <c:forEach var="periodical" items="${requestScope.periodicals}">
                <tr>
                    <th>${periodical.name}</th>
                    <th>${periodical.author}</th>
                    <th>${periodical.publishYear}</th>
                    <th>${periodical.type}</th>
                    <th>${periodical.subCost}</th>
                    <th>${periodical.publisher}</th>
                    <th>
                        <a href="#">Subscribe</a>
                    </th>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>
