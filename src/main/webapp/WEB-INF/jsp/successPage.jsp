<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 31.03.2021
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.success"/></title>
    <link href="${pageContext.request.contextPath}/css/successPage.css" rel="stylesheet" type="text/css">
</head>
<c:if test="${sessionScope.role == 'ADMIN'}">
    <jsp:include page="/WEB-INF/jsp/admin/adminHeader.jsp"/>
</c:if>
<c:if test="${sessionScope.role == 'USER'}">
    <jsp:include page="/WEB-INF/jsp/user/userHeader.jsp"/>
</c:if>
<body>
    <div class="page-text">
        <p class="main-text"><fmt:message key="success.main"/></p>
        <p class="request-text">${successMessage}</p>
    </div>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
