<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 07.03.2021
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.errorpage"/></title>
    <link href="${pageContext.request.contextPath}/css/errorPage.css" rel="stylesheet" type="text/css">
</head>
<c:if test="${sessionScope.role == 'ADMIN'}">
    <jsp:include page="/WEB-INF/jsp/admin/adminHeader.jsp"/>
</c:if>
<c:if test="${sessionScope.role == 'USER'}">
    <jsp:include page="/WEB-INF/jsp/user/userHeader.jsp"/>
</c:if>
<body>
    <div class="main">
        <h1>${pageContext.errorData.statusCode}!</h1>
        <br/>
        <p><fmt:message key="error.request"/>: ${pageContext.errorData.requestURI}</p>
        <br/>
        <p><fmt:message key="error.servlet"/>: ${pageContext.errorData.servletName}</p>
        <br/>
        <p><fmt:message key="error.exception"/>: ${pageContext.exception}</p>
        <br/>
        <p>${errorMessage}</p>
    </div>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
