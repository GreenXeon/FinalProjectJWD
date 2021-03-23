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
    <title>Main user page</title>
    <link href="${pageContext.request.contextPath}/css/mainUserPage.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="userHeader.jsp"/>
<body>
<c:if test="${not empty requestScope.periodicals}">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="show_subscribe" />
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
                    <th>Choose</th>
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
                            <label>
                                <input type="checkbox" name="selected" value="${periodical.id}">
                            </label>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="errorMessage">
                    ${errorMessage}
            </div>
            <button type="submit" class="submit-button">Subscribe</button>
        </div>
    </form>
</c:if>
</body>
</html>
