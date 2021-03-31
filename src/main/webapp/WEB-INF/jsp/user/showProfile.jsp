<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 26.03.2021
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User profile</title>
    <link href="${pageContext.request.contextPath}/css/showProfile.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="userHeader.jsp"/>
<body>
<div class="caption">
    <h1>Profile</h1>
</div>
<div class="main-info">
    <c:if test="${not empty requestScope.user}">
        <form action="controller">
            <input type="hidden" name="command" value="show_update_user">
            <p>Login:
                <strong>${requestScope.user.login}</strong>
            </p>
            <p>Password: <i>hidden</i></p>
            <p>Name:
                <c:if test="${not empty requestScope.user.name}">
                    <strong>${requestScope.user.name}</strong>
                </c:if>
                <c:if test="${empty requestScope.user.name}">
                    <i>empty</i>
                </c:if>
            </p>
            <p>Surname:
                <c:if test="${not empty requestScope.user.surname}">
                    <strong>${requestScope.user.surname}</strong>
                </c:if>
                <c:if test="${empty requestScope.user.surname}">
                    <i>empty</i>
                </c:if>
            </p>
            <p>E-mail:
                <c:if test="${not empty requestScope.user.email}">
                    <strong>${requestScope.user.email}</strong>
                </c:if>
                <c:if test="${empty requestScope.user.email}">
                    <i>empty</i>
                </c:if>
            </p>
            <p>Your balance is
                <strong>${requestScope.user.cash} BYN</strong>
            </p>
            <br>
            <hr>
            <button>Change profile</button>
        </form>
        <hr>
        <form action="controller">
            <input type="hidden" name="command" value="top_up_balance">
            <div class="cash-button">
                <label>
                    <input type="number" step="0.1" name="balancer">
                </label>
                <br>
                <button>Top up balance</button>
            </div>
        </form>
    </c:if>
</div>
</body>
</html>
