<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 03.03.2021
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.login"/></title>
    <link href="${pageContext.request.contextPath}css/loginPage.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="indexHeader.jsp"/>
<body>
<div class="main">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="login" />
        <label><fmt:message key="form.login"/></label>
        <br>
        <input name="login" size="30" required pattern="^[a-zA-Z0-9]{5,50}$"/>
        <br><br>
        <label><fmt:message key="form.password"/></label>
        <br>
        <input type="password" name="password" size="30" required pattern="^[a-zA-Z0-9#№.,-=:;!?&]{6,50}$"/>
        <br><br>
        <label class="remember-me">
            <fmt:message key="form.rememberme"/>
            <input type="checkbox" name="remember">
        </label>
        <br><br>
        <button type="submit"><fmt:message key="button.signin"/></button>
        <br>
        ${errorMessage}
    </form>
</div>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
