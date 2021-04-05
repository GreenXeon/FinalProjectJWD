
<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 31.03.2021
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<c:if test="${not empty requestScope.payment-type}">
    ${requestScope.payment-type}
</c:if>
</body>
</html>
