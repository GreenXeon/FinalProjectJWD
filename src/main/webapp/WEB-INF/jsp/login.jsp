<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 03.03.2021
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
    <link href="${pageContext.request.contextPath}css/loginPage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="login" />
        <label>Login</label>
        <br>
        <input name="login" size="30" />
        <br><br>
        <label>Password</label>
        <br>
        <input type="password" name="password" size="30" />
        <br><br>
        <button type="submit">Sign in</button>
        <br>
        ${errorMessage}
    </form>
</div>
</body>
</html>
