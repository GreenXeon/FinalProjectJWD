<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Periodicals subscription</title>
    <link href="${pageContext.request.contextPath}/css/mainPage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main">
    <h1>Welcome to the periodicals subscription!</h1>
    <h3>Choose an action</h3>
    <form action="controller" method="post">
        <button name="login">Sign in</button>
        <input type="hidden" name="command" value="showlogin">
    </form>
    <h3>or</h3>
    <form action="controller" method="post">
        <button name="signup">Sign up</button>
        <input type="hidden" name="command" value="showsignup">
    </form>
</div>
</body>
</html>