<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 22.03.2021
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/subscription.ico" />
<link href="${pageContext.request.contextPath}/css/userHeader.css" rel="stylesheet" type="text/css">
<header class="site-header">
    <div class="page-title"><h2><fmt:message key="userheader.title"/></h2></div>
    <span class="menu">
        <nav>
            <a href="${pageContext.request.contextPath}/controller?command=show_user_main"><fmt:message key="userheader.periodicals"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=show_orders"><fmt:message key="userheader.orders"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=show_profile_user"><fmt:message key="userheader.profile"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="userheader.logout"/></a>
        </nav>
    </span>
</header>