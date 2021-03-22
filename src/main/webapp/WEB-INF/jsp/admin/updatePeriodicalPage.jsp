<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 20.03.2021
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Change periodical</title>
    <link href="${pageContext.request.contextPath}/css/changePeriodicalPage.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="adminHeader.jsp"/>
<body>
<c:if test="${not empty requestScope.periodical}">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="update_periodical" />
        <input type="hidden" name="periodicalId" value="${requestScope.periodical.id}">
        <label>Name</label>
        <input name="name" size="30" value="${requestScope.periodical.name}" required pattern="^[a-zA-Z0-9#№.,-=:;!?& ]{1,50}$"/>
        <br>
        <label>Author</label>
        <input name="author" size="30" value="${requestScope.periodical.author}" required pattern="^[a-zA-Z ]{1,50}$"/>
        <br>
        <label>Publish year</label>
        <input type="date" name="publishDate" id="datePicker" size="30" value="${requestScope.periodical.publishDate}" required pattern=""/>
        <br>
        <label>Type</label>
        <select name="type" value="${requestScope.periodical.type.i}" id="choice-box-menu-item">
            <option value="1">Newspaper</option>
            <option value="2">Magazine</option>
            <option value="3">Comics</option>
        </select>
        <br>
        <label>Cost</label>
        <input name="cost" size="30" value="${requestScope.periodical.subCost}" required pattern="\d+(\.\d{1,2})?"/>
        <br>
        <label>Publisher</label>
        <input name="publisher" size="30" value="${requestScope.periodical.publisher}" required pattern="^[a-zA-Z0-9 ]{1,50}$"/>
        <br>
        <button type="submit">Change</button>
        <br>
            ${errorMessage}
    </form>
</c:if>
</body>
</html>
