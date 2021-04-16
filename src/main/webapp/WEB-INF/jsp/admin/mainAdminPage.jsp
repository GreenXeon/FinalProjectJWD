<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 16.03.2021
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.admin"/></title>
    <link href="${pageContext.request.contextPath}/css/mainAdminPage.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="adminHeader.jsp"/>
<body>
    <h1 class="caption"><fmt:message key="caption.periodicals"/></h1>
    <div class="add-button">
        <a href="${pageContext.request.contextPath}/controller?command=show_add_per"><fmt:message key="button.add.periodical"/></a>
    </div>
    <hr>
    <div class="find-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="show_per_admin" />
            <input name="finder" size="30" required/>
            <br><br>
            <button><fmt:message key="button.find"/></button>
            <br><br>
        </form>
    </div>
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
                <th><fmt:message key="periodical.change"/></th>
                <th><fmt:message key="periodical.delete"/></th>
            </tr>
            <c:forEach var="periodical" items="${requestScope.periodicals}">
                <tr>
                    <td>${periodical.name}</td>
                    <td>${periodical.author}</td>
                    <td>${periodical.publishDate}</td>
                    <td>${periodical.type}</td>
                    <td>${periodical.subCost} <fmt:message key="currency"/></td>
                    <td>${periodical.publisher}</td>
                    <td>
                        <c:url value="${pageContext.request.contextPath}/controller" var="url">
                            <c:param name="command" value="show_update_periodical" />
                            <c:param name="periodicalName" value="${periodical.name}" />
                        </c:url>
                        <a href="${url}"><fmt:message key="button.change.periodical"/></a>
                    </td>
                    <td>
                        <c:url value="${pageContext.request.contextPath}/controller" var="url">
                            <c:param name="command" value="delete_periodical" />
                            <c:param name="periodicalId" value="${periodical.id}" />
                        </c:url>
                        <a href="${url}"><fmt:message key="button.delete.periodical"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
