<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 16.03.2021
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/adminHeader.css" rel="stylesheet" type="text/css">
<header class="site-header">
    <div class="page-title"><h2>Periodicals Subscription Admin Panel</h2></div>
    <span class="menu">
        <nav>
            <a href="${pageContext.request.contextPath}/controller?command=show_per_admin">Periodicals</a>
            <a href="${pageContext.request.contextPath}/controller?command=show_all_users">Users</a>
            <a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a>
        </nav>
    </span>
</header>