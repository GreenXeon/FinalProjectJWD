<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 09.03.2021
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link href="${pageContext.request.contextPath}css/signUpPage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="signup" />
        <label>Enter login</label>
        <br>
        <input name="login" size="30" />
        <br><br>
        <label>Enter password</label>
        <br>
        <input type="password" name="password" size="30" />
        <br><br>
        <label>Repeat password</label>
        <br>
        <input type="password" name="passwordSecond" size="30" />
        <br><br>
        <button type="submit">Sign in</button>
        <br>
        ${errorMessage}
    </form>
</div>
</body>
</html>
