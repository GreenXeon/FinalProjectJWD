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
        <h2 class="caption">Periodicals</h2>
        <div class="add-button">
            <a href="${pageContext.request.contextPath}/controller?command=show_add_per">Add periodical</a>
        </div>
        <table>
            <tr>
                <th>Name</th>
                <th>Author</th>
                <th>Publish date</th>
                <th>Type</th>
                <th>Cost</th>
                <th>Publisher</th>
                <th>Change</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="periodical" items="${requestScope.periodicals}">
                <tr>
                    <td>${periodical.name}</td>
                    <td>${periodical.author}</td>
                    <td>${periodical.publishDate}</td>
                    <td>${periodical.type}</td>
                    <td>${periodical.subCost}</td>
                    <td>${periodical.publisher}</td>
                    <td>
                        <c:url value="${pageContext.request.contextPath}/controller" var="url">
                            <c:param name="command" value="show_change_periodical" />
                            <c:param name="periodicalName" value="${periodical.name}" />
                        </c:url>
                        <a href="${url}">Change</a>
                    </td>
                    <td>
                        <a href="#">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>
