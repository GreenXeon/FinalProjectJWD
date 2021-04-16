<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 26.03.2021
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.update.profile"/></title>
    <link href="${pageContext.request.contextPath}/css/showUpdateProfile.css" rel="stylesheet" type="text/css">
</head>
<c:if test="${sessionScope.role == 'ADMIN'}">
    <jsp:include page="/WEB-INF/jsp/admin/adminHeader.jsp"/>
</c:if>
<c:if test="${sessionScope.role == 'USER'}">
    <jsp:include page="user/userHeader.jsp"/>
</c:if>
<body>
<h1><fmt:message key="caption.profile"/></h1>
<c:if test="${not empty requestScope.user}">
    <div class="main">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="update_user" />
            <label><fmt:message key="form.login"/></label>
            <br>
            <input name="login" size="30" value="${requestScope.user.login}" required pattern="^[a-zA-Z0-9]{5,50}$"/>
            <br>
            <label><fmt:message key="form.name"/></label>
            <br>
            <input name="name" size="30" value="${requestScope.user.name}" required pattern="^[a-zA-Z-]{1,20}$"/>
            <br>
            <label><fmt:message key="form.surname"/></label>
            <br>
            <input name="surname" size="30" value="${requestScope.user.surname}" required pattern="^[a-zA-Z-]{1,20}$"/>
            <br>
            <label><fmt:message key="form.email"/></label>
            <br>
            <input type="email" name="email" size="30" value="${requestScope.user.email}" required pattern="^[([a-zA-Z0-9_.-]+)@([a-zA-Z0-9.-]{4,})]{6,45}$"/>
            <br>
            <button><fmt:message key="button.update.user"/></button>
            <br><br>
            <h3>${errorMessage}</h3>
        </form>
    </div>
    <hr>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="show_change_password" />
        <button class="change-password"><fmt:message key="button.change.password"/></button>
    </form>
</c:if>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
