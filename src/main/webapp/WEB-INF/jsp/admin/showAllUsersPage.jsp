<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 18.03.2021
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="pagetitle.all.users"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="${pageContext.request.contextPath}/css/showAllUsers.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="adminHeader.jsp"/>
<body>

    <div class="caption"><h1><fmt:message key="caption.users"/></h1></div>
    <br>
    <div class="find-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="show_all_users" />
            <input name="finder" size="30" required/>
            <br><br>
            <button><fmt:message key="button.find"/></button>
            <br>
        </form>
    </div>
    <hr>
    <h3>${errorMessage}</h3>
    <br>
    <c:if test="${not empty requestScope.users}">
        <div class="users">
            <table>
                <tr>
                    <th><fmt:message key="user.login"/></th>
                    <th><fmt:message key="user.name"/></th>
                    <th><fmt:message key="user.surname"/></th>
                    <th><fmt:message key="user.email"/></th>
                    <th><fmt:message key="user.registration.date"/></th>
                    <th><fmt:message key="user.balance"/></th>
                    <th><fmt:message key="user.role"/></th>
                    <th><fmt:message key="user.banned"/></th>
                    <th><fmt:message key="user.ban"/></th>
                    <th><fmt:message key="user.make.admin"/></th>
                </tr>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td>${user.login}</td>
                        <td>
                            <c:if test="${user.name == null}">
                                -
                            </c:if>
                            <c:if test="${user.name != null}">
                                ${user.name}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${user.surname == null}">
                            -
                            </c:if>
                            <c:if test="${user.surname != null}">
                                ${user.surname}
                            </c:if></td>
                        <td>${user.email}</td>
                        <td>${user.registrationDate}</td>
                        <td>${user.cash}</td>
                        <td>${user.role}</td>
                        <td>
                            <c:if test="${user.blocked == false}">
                                <fmt:message key="banned.no"/>
                            </c:if>
                            <c:if test="${user.blocked == true}">
                                <fmt:message key="banned.yes"/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${sessionScope.userId != user.id}">
                                <c:if test="${user.blocked == false}">
                                    <c:url value="${pageContext.request.contextPath}/controller" var="url">
                                        <c:param name="command" value="ban_user" />
                                        <c:param name="userId" value="${user.id}" />
                                    </c:url>
                                    <a href="${url}"><fmt:message key="button.ban"/></a>
                                </c:if>
                                <c:if test="${user.blocked == true}">
                                    <c:url value="${pageContext.request.contextPath}/controller" var="url">
                                        <c:param name="command" value="unban_user" />
                                        <c:param name="userId" value="${user.id}" />
                                    </c:url>
                                    <a href="${url}"><fmt:message key="button.unban"/></a>
                                </c:if>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${sessionScope.userId != user.id}">
                                <c:if test="${user.role.i == 1}">
                                    <c:url value="${pageContext.request.contextPath}/controller" var="url">
                                        <c:param name="command" value="make_admin" />
                                        <c:param name="userId" value="${user.id}" />
                                    </c:url>
                                    <a href="${url}"><fmt:message key="button.make.admin"/></a>
                                </c:if>

                                <c:if test="${user.role.i == 2}">
                                    <c:url value="${pageContext.request.contextPath}/controller" var="url">
                                        <c:param name="command" value="make_user" />
                                        <c:param name="userId" value="${user.id}" />
                                    </c:url>
                                    <a href="${url}"><fmt:message key="button.make.user"/></a>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</html>
