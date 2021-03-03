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
    <title>Title</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="login" />
    <label>Email:</label>
    <input name="email" size="30" />
    <br><br>
    <label>Password:</label>
    <input type="password" name="password" size="30" />
    <br><br>
    <button type="submit">Login</button>
</form>
</body>
</html>
