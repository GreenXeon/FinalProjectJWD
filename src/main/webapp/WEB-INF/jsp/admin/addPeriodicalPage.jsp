<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 17.03.2021
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/todayPicker.js"></script>
    <link href="${pageContext.request.contextPath}/css/addPeriodicalPage.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="adminHeader.jsp"/>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="add_periodical" />
    <label>Name</label>
    <input name="name" size="30" required pattern="^[a-zA-Z0-9#№.,-=:;!?& ]{1,50}$"/>
    <br>
    <label>Author</label>
    <input name="author" size="30" required pattern="^[a-zA-Z ]{1,50}$"/>
    <br>
    <label>Publish year</label>
    <input type="date" name="publishDate" id="datePicker" size="30" required pattern=""/>
    <br>
    <label>Type</label>
    <select name="type" id="choice-box-menu-item">
        <option value="1">Newspaper</option>
        <option value="2">Magazine</option>
        <option value="3">Comics</option>
    </select>
    <br>
    <label>Cost</label>
    <input name="cost" size="30" required pattern="\d+(\.\d{1,2})?"/>
    <br>
    <label>Publisher</label>
    <input name="publisher" size="30" required pattern="^[a-zA-Z0-9 ]{1,50}$"/>
    <br>
    <button type="submit">Add</button>
    <br>
    ${errorMessage}
</form>
</body>
</html>
