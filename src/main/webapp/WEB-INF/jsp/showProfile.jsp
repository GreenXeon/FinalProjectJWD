<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 26.03.2021
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.profile"/></title>
    <link href="${pageContext.request.contextPath}/css/showProfile.css" rel="stylesheet" type="text/css">
</head>
<c:if test="${sessionScope.role == 'ADMIN'}">
    <jsp:include page="/WEB-INF/jsp/admin/adminHeader.jsp"/>
</c:if>
<c:if test="${sessionScope.role == 'USER'}">
    <jsp:include page="user/userHeader.jsp"/>
</c:if>
<body>
<div class="caption">
    <h1><fmt:message key="caption.profile"/></h1>
</div>
<h3>${errorMessage}</h3>
<div class="main-info">
    <c:if test="${not empty requestScope.user}">
        <form action="controller">
            <input type="hidden" name="command" value="show_update_user">
            <p><fmt:message key="form.login"/>:
                <strong>${requestScope.user.login}</strong>
            </p>
            <p><fmt:message key="form.password"/>: <i><fmt:message key="form.hidden"/></i></p>
            <p><fmt:message key="form.name"/>:
                <c:if test="${not empty requestScope.user.name}">
                    <strong>${requestScope.user.name}</strong>
                </c:if>
                <c:if test="${empty requestScope.user.name}">
                    <i><fmt:message key="form.empty"/></i>
                </c:if>
            </p>
            <p><fmt:message key="form.surname"/>:
                <c:if test="${not empty requestScope.user.surname}">
                    <strong>${requestScope.user.surname}</strong>
                </c:if>
                <c:if test="${empty requestScope.user.surname}">
                    <i><fmt:message key="form.empty"/></i>
                </c:if>
            </p>
            <p><fmt:message key="form.email"/>:
                <c:if test="${not empty requestScope.user.email}">
                    <strong>${requestScope.user.email}</strong>
                </c:if>
                <c:if test="${empty requestScope.user.email}">
                    <i><fmt:message key="form.empty"/></i>
                </c:if>
            </p>
            <p><fmt:message key="form.balance"/>
                <strong>${requestScope.user.cash} <fmt:message key="currency"/></strong>
            </p>
            <br>
            <hr>
            <button><fmt:message key="button.change.profile"/></button>
        </form>
        <hr>
        <form action="controller">
            <input type="hidden" name="command" value="top_up_balance">
            <div class="cash-button">
                <label>
                    <input type="number" step="0.1" name="balancer">
                </label>
                <br>
                <button><fmt:message key="button.topup"/></button>
            </div>
        </form>
        <hr>
        <form action="controller">
            <input type="hidden" name="command" value="change_language">
            <div class="lang-button">
                <select name="lang" id="choice-box-menu-item">
                    <option value="1"><fmt:message key="lang.english"/></option>
                    <option value="2"><fmt:message key="lang.russian"/></option>
                    <option value="3"><fmt:message key="lang.hebrew"/></option>
                </select>
                <br>
                <button><fmt:message key="button.change.lang"/></button>
            </div>
        </form>
    </c:if>
</div>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
