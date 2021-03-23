<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 23.03.2021
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your order</title>
    <link href="${pageContext.request.contextPath}/css/showSubscription.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="userHeader.jsp"/>
<body>
<div class="main-info">
    <h1 class="caption">Your order</h1>
        <c:if test="${not empty requestScope.subscribePeriodicals}">
            <ul class="list">
                <c:forEach var="periodical" items="${subscribePeriodicals}">
                    <li>
                            <h2 class="periodical-name">${periodical.name}</h2> <br>
                            by ${periodical.author}<br><br>
                            <span class="item-cost">${periodical.subCost} BYN</span>
                    </li>
                    <c:set var="totalCost" value="${totalCost + periodical.subCost}" />
                </c:forEach>
            </ul>
            <div class="total-cost">
                Total cost is
                    <span>${totalCost} BYN</span>
                <br>
                Your cash is <span>${requestScope.user.cash} BYN</span>
            </div>
            <hr>
            <div class="buttons">
                <button type="submit" class="pay-button">Subscribe</button>
                <button type="submit" class="credit-button">Buy in credit</button>
            </div>
        </c:if>
</div>
</body>
</html>
