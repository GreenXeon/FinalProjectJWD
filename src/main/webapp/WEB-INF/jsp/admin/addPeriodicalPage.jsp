<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 17.03.2021
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.add.periodical"/></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/todayPicker.js"></script>
    <link href="${pageContext.request.contextPath}/css/addPeriodicalPage.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="adminHeader.jsp"/>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="add_periodical" />
    <label><fmt:message key="periodical.name"/></label>
    <br>
        <input name="name" size="30" required pattern="^[a-zA-Z0-9#№.,-=:;!?& ]{1,50}$"/>
    <br>
    <label><fmt:message key="periodical.author"/></label>
    <br>
        <input name="author" size="30" required pattern="^[a-zA-Z ]{1,50}$"/>
    <br>
    <label><fmt:message key="periodical.publishDate"/></label>
    <br>
        <input type="date" name="publishDate" id="datePicker" size="30" required />
    <br>
    <label><fmt:message key="periodical.type"/></label>
    <br>
    <select name="type" id="choice-box-menu-item">
        <option value="1"><fmt:message key="periodical.type.newspaper"/></option>
        <option value="2"><fmt:message key="periodical.type.magazine"/></option>
        <option value="3"><fmt:message key="periodical.type.comics"/></option>
    </select>
    <br>
    <label><fmt:message key="periodical.cost"/></label>
    <br>
        <input name="cost" size="30" required pattern="\d{1,4}(\.\d{1,2})?"/>
    <br>
    <label><fmt:message key="periodical.publisher"/></label>
    <br>
        <input name="publisher" size="30" required pattern="^[a-zA-Z0-9 ]{1,50}$"/>
    <br>
    <button type="submit"><fmt:message key="button.add.periodical"/></button>
    <br>
    ${errorMessage}
</form>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
