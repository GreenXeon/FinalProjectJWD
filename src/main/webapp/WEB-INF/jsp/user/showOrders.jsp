<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 12.04.2021
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.orders"/></title>
    <link href="${pageContext.request.contextPath}/css/showOrders.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="userHeader.jsp"/>
<body>
    <h1 class="caption"><fmt:message key="caption.subscriptions"/></h1>
    <div class="find-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="show_orders" />
            <input name="finder" size="30" required/>
            <br><br>
            <button><fmt:message key="button.find"/></button>
            <br>
        </form>
    </div>
    <h3>${errorMessage}</h3>
    <hr>
    <c:if test="${not empty requestScope.userSubscriptions}">
        <div class="orders">
            <table>
                <tr>
                    <th><fmt:message key="subscription.id"/></th>
                    <th><fmt:message key="subscription.periodical"/></th>
                    <th><fmt:message key="subscription.payment"/></th>
                    <th><fmt:message key="subscription.date"/></th>
                    <th><fmt:message key="subscription.cost"/></th>
                </tr>
                <c:forEach var="subscription" items="${requestScope.userSubscriptions}">
                    <tr>
                        <td>${subscription.id}</td>
                        <td style="font-weight: bold">${subscription.periodicalName}</td>
                        <td>${subscription.paymentId}</td>
                        <td>${subscription.subscriptionDate}</td>
                        <td>${subscription.subscriptionCost} <fmt:message key="currency"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
