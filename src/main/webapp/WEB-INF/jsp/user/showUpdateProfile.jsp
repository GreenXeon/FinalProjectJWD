<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 26.03.2021
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update profile</title>
    <link href="${pageContext.request.contextPath}/css/showUpdateProfile.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="userHeader.jsp"/>
<body>
<h1>Profile</h1>
<c:if test="${not empty requestScope.user}">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="update_user" />
        <label>Login</label>
        <input name="login" size="30" value="${requestScope.user.login}" required pattern="^[a-zA-Z0-9]{5,30}$"/>
        <br>
        <label>Name</label>
        <input name="name" size="30" value="${requestScope.user.name}" required pattern="^[a-zA-Z-]{1,20}$"/>
        <br>
        <label>Surname</label>
        <input name="surname" size="30" value="${requestScope.user.surname}" required pattern="^[a-zA-Z-]{1,20}$"/>
        <br>
        <label>E-mail</label>
        <input type="email" name="email" size="30" value="${requestScope.user.email}" required />
        <br>
        <button>Update info</button>
        <br>
            ${errorMessage}
    </form>
</c:if>
</body>
</html>
