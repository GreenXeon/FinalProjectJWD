<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 16.03.2021
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<link href="${pageContext.request.contextPath}/css/adminHeader.css" rel="stylesheet" type="text/css">
<header class="site-header">
    <div class="page-title"><h2><fmt:message key="admin.header.title"/></h2></div>
    <span class="menu">
        <nav>
            <a href="${pageContext.request.contextPath}/controller?command=show_per_admin"><fmt:message key="admin.header.periodicals"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=show_all_users"><fmt:message key="admin.header.users"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=show_profile_user"><fmt:message key="admin.header.profile"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="admin.header.logout"/></a>
        </nav>
    </span>
</header>
