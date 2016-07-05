<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="utf-8" %>
<html>
<head>
    <spring:url value="/resources/style.css" var="coreCss" />
    <link href="${coreCss}" rel="stylesheet" />
</head>
<body>
<div id="left">
    <div id="back">
        <a href="#" onclick="window.history.back();"><-Вернуться</a>
    </div>
</div>
<div id="top">
    <label>Собеседники</label>
    <hr/>
</div>
<div id="center-down">
    <ul>
        <div id="news-buttons">
            <c:forEach items="${users}" var="user">
            <li><a href="/showChat?fullName=${user}"><c:out value="${user}"/></a>
                </c:forEach>
        </div>
    </ul>
</div>
<div id="right">
    <ul>
        <div class="header">
            <li><label id="name"><c:out value="${name}"/></label><label id="lastName"><c:out value="${lastName}"/></label>
        </div>
        <br/>
        <li><a href="/showBuddyList">Мои сообщения</a>
        <li><a href="/informationStudent">Информация</a>
        <li><a href="/editStudent">Редактирование</a>
        <li><a href="/logOut">Выход</a>
    </ul>
</div>
</body>
</html>