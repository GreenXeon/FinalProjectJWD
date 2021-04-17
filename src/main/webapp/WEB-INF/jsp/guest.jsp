<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 15.03.2021
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.guest"/></title>
    <link rel="icon" type="image/ico" href="${pageContext.request.contextPath}/img/subscription.ico" />
    <link href="${pageContext.request.contextPath}/css/guestPage.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="indexHeader.jsp"/>
<body>
    <h1 class="caption"><fmt:message key="caption.periodicals"/></h1>
    <div class="find-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="showguest" />
            <input name="finder" size="30" required/>
            <br><br>
            <button><fmt:message key="button.find"/></button>
            <br>
        </form>
    </div>
    <hr>
    <c:if test="${not empty requestScope.periodicals}">
        <div class="periodicals">
            <table>
                <tr>
                    <th><fmt:message key="periodical.name"/></th>
                    <th><fmt:message key="periodical.author"/></th>
                    <th><fmt:message key="periodical.publishDate"/></th>
                    <th><fmt:message key="periodical.type"/></th>
                    <th><fmt:message key="periodical.cost"/></th>
                    <th><fmt:message key="periodical.publisher"/></th>
                </tr>
                <c:forEach var="periodical" items="${requestScope.periodicals}">
                    <tr>
                        <td>${periodical.name}</td>
                        <td>${periodical.author}</td>
                        <td>${periodical.publishDate}</td>
                        <td>${periodical.type}</td>
                        <td>${periodical.subCost} <fmt:message key="currency"/></td>
                        <td>${periodical.publisher}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
