<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 20.03.2021
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.change.periodical"/></title>
    <link href="${pageContext.request.contextPath}/css/changePeriodicalPage.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="adminHeader.jsp"/>
<body>
<c:if test="${not empty requestScope.periodical}">
    <h3>${errorMessage}</h3>
    <div class="main">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="update_periodical" />
            <input type="hidden" name="periodicalId" value="${requestScope.periodical.id}">
            <label><fmt:message key="periodical.name"/></label>
            <br>
            <input name="name" size="30" value="${requestScope.periodical.name}" required pattern="^[a-zA-Z0-9#№.,-=:;!?& ]{1,50}$"/>
            <br>
            <label><fmt:message key="periodical.author"/></label>
            <br>
            <input name="author" size="30" value="${requestScope.periodical.author}" required pattern="^[a-zA-Z ]{1,50}$">
            <br>
            <label><fmt:message key="periodical.publishDate"/></label>
            <br>
            <input type="date" name="publishDate" id="datePicker" size="30" value="${requestScope.periodical.publishDate}" required />
            <br>
            <label><fmt:message key="periodical.type"/></label>
            <br>
            <select name="type" value="${requestScope.periodical.type.i}" id="choice-box-menu-item">
                <option value="1"><fmt:message key="periodical.type.newspaper"/></option>
                <option value="2"><fmt:message key="periodical.type.magazine"/></option>
                <option value="3"><fmt:message key="periodical.type.comics"/></option>
            </select>
            <br>
            <label><fmt:message key="periodical.cost"/></label>
            <br>
            <input name="cost" size="30" value="${requestScope.periodical.subCost}" required pattern="\d{1,4}(\.\d{1,2})?"/>
            <br>
            <label><fmt:message key="periodical.publisher"/></label>
            <br>
            <input name="publisher" size="30" value="${requestScope.periodical.publisher}" required pattern="^[a-zA-Z0-9., ]{1,50}$"/>
            <br>
            <button type="submit"><fmt:message key="button.change.periodical"/></button>
            <br>
        </form>
    </div>
</c:if>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
