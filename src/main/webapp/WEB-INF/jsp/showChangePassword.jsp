<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 13.04.2021
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="caption.changepassword"/></title>
    <link href="${pageContext.request.contextPath}/css/showChangePassword.css" rel="stylesheet" type="text/css">
</head>
<c:if test="${sessionScope.role == 'ADMIN'}">
    <jsp:include page="admin/adminHeader.jsp"/>
</c:if>
<c:if test="${sessionScope.role == 'USER'}">
    <jsp:include page="user/userHeader.jsp"/>
</c:if>
<body>
    <div class="main">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="change_password" />
            <label><fmt:message key="form.change.password"/></label>
            <br>
            <input name="pass" type="password" size="30" required pattern="^[a-zA-Z0-9#№.,-=:;!?&]{6,50}$"/>
            <br>
            <label><fmt:message key="form.change.password.repeat"/></label>
            <br>
            <input name="rep_pass" type="password" size="30" required pattern="^[a-zA-Z0-9#№.,-=:;!?&]{6,50}$"/>
            <br>
            <h3>${errorMessage}</h3>
            <br>
            <button class="change-password"><fmt:message key="button.change.password"/></button>
        </form>
    </div>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
