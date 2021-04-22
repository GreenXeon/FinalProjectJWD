<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 11.03.2021
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.user.main"/></title>
    <link href="${pageContext.request.contextPath}/css/mainUserPage.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="userHeader.jsp"/>
<body>
    <h1 class="caption"><fmt:message key="caption.periodicals"/></h1>
    <div class="find-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="show_user_main" />
            <input name="finder" size="30" required/>
            <br><br>
            <button><fmt:message key="button.find"/></button>
            <br>
        </form>
    </div>
    <hr>
    <div class="errorMessage">
        <h3>${errorMessage}</h3>
    </div>
    <c:if test="${not empty requestScope.periodicals}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="show_subscribe" />
            <div class="periodicals">
                <table>
                    <tr>
                        <th><fmt:message key="periodical.name"/></th>
                        <th><fmt:message key="periodical.author"/></th>
                        <th><fmt:message key="periodical.publishDate"/></th>
                        <th><fmt:message key="periodical.type"/></th>
                        <th><fmt:message key="periodical.cost"/></th>
                        <th><fmt:message key="periodical.publisher"/></th>
                        <th><fmt:message key="periodical.choose"/></th>
                    </tr>
                    <c:forEach var="periodical" items="${requestScope.periodicals}">
                        <tr>
                            <td>${periodical.name}</td>
                            <td>${periodical.author}</td>
                            <td>${periodical.publishDate}</td>
                            <td>${periodical.type}</td>
                            <td>${periodical.subCost} BYN</td>
                            <td>${periodical.publisher}</td>
                            <td>
                                <label>
                                    <input type="checkbox" name="selected" value="${periodical.id}">
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <button type="submit" class="submit-button"><fmt:message key="button.subscribe"/></button>
            </div>
        </form>
    </c:if>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
