<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 23.03.2021
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.subscription"/></title>
    <link href="${pageContext.request.contextPath}/css/showSubscription.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="userHeader.jsp"/>
<body>
    <div class="main-info">
        <h1 class="caption"><fmt:message key="caption.subscription"/></h1>
            <c:if test="${not empty sessionScope.subscribePeriodicals}">
                <ul class="list">
                    <c:forEach var="periodical" items="${sessionScope.subscribePeriodicals}">
                        <li>
                                <h2 class="periodical-name">${periodical.name}</h2> <br>
                            <fmt:message key="subscription.byauthor"/> ${periodical.author}<br><br>
                                <span class="item-cost">${periodical.subCost} <fmt:message key="currency"/></span>
                        </li>
                    </c:forEach>
                </ul>
                <div class="total-cost">
                    <fmt:message key="subscription.totalcost"/>
                        <span>${sessionScope.totalCost} <fmt:message key="currency"/></span>
                    <br>
                    <fmt:message key="subscription.balance"/> <span>${requestScope.user.cash} <fmt:message key="currency"/></span>
                </div>
                <hr>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="subscribe" />
                    <div class="buttons">
                        <c:if test="${sessionScope.totalCost > requestScope.user.cash}">
                            <p><input type="radio" name="payment-type" disabled value="online"><fmt:message key="payment.pay.online"/></p>
                            <fmt:message key="payment.funds.not.enough"/>
                            <br>
                            <p><input type="radio" name="payment-type" value="credit" checked><fmt:message key="payment.pay.credit"/></p>
                        </c:if>
                        <c:if test="${sessionScope.totalCost <= requestScope.user.cash}">
                            <p><input type="radio" name="payment-type" value="online" checked><fmt:message key="payment.pay.online"/></p>
                            <p><input type="radio" name="payment-type" value="credit"><fmt:message key="payment.pay.credit"/></p>
                        </c:if>
                        <br>
                        <button type="submit" class="pay-button"><fmt:message key="button.subscribe"/></button>
                    </div>
                </form>
            </c:if>
    </div>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
