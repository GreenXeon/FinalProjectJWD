<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 09.03.2021
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.signup"/></title>
    <link href="${pageContext.request.contextPath}/css/signUpPage.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="indexHeader.jsp"/>
<body>
<div class="main">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="signup" />
        <label><fmt:message key="form.signup.login"/></label>
        <br>
        <input name="login" size="30" required pattern="^[a-zA-Z0-9]{5,50}$"/>
        <br><br>
        <label><fmt:message key="form.signup.email"/></label>
        <br>
        <input type="email" name="email" size="30" required pattern="^[([a-zA-Z0-9_.-]+)@([a-zA-Z0-9.-]{4,})]{6,45}$"/>
        <br><br>
        <label><fmt:message key="form.signup.password"/></label>
        <br>
        <input type="password" name="password" size="30" required pattern="^[a-zA-Z0-9#№.,-=:;!?&]{6,50}$"/>
        <br><br>
        <label><fmt:message key="form.signup.repeatpassword"/></label>
        <br>
        <input type="password" name="passwordSecond" size="30" required pattern="^[a-zA-Z0-9#№.,-=:;!?&]{6,50}$"/>
        <br><br>
        <button type="submit"><fmt:message key="button.signup"/></button>
        <br>
        <h3>${errorMessage}</h3>
    </form>
</div>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
