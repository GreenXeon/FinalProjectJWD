<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 12.03.2021
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<link href="${pageContext.request.contextPath}/css/indexHeader.css" rel="stylesheet" type="text/css">
<header class="site-header">
    <div class="page-title"><h2><fmt:message key="userheader.title"/></h2></div>
    <span class="menu">
        <nav>
            <a href="${pageContext.request.contextPath}/controller?command=showguest"><fmt:message key="userheader.main"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=showlogin"><fmt:message key="userheader.signin"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=showsignup"><fmt:message key="userheader.signup"/></a>
        </nav>
    </span>
</header>