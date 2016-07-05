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
<div id="top-left">
<div class="header">
<label>Группа - <c:out value="${group}"></c:out></label>
</div>
<hr/>
</div>
<div id="left-down">
<div class="header">
<label>Студенты</label>
</div>
<ul>
    <c:forEach items="${students}" var="student">
<li><a href="/selectStudent?fullName=${student}"><c:out value="${student}"></c:out></a>
    </c:forEach>
</ul>
<div class="header">
<label>Преподаватели</label>
</div>
<ul>
    <c:forEach items="${teachers}" var="teacher">
    <li><a href="/selectTeacher?fullName=${teacher}"><c:out value="${teacher}"></c:out></a>
        </c:forEach>
</ul>
<div id="back">
<a href="#" onclick="window.history.back();"><-Выбрать группу</a>
</div>
</div>
<div id="top">
<label>Новости</label>
<hr/>
</div>
<div id="center">
<ul>
<div id="news-buttons">
    <c:forEach items="${news}" var="item">
    <li><a href="/showNewsChat?nameNews=${item}&nameGroup=${group}"><c:out value="${item}"></c:out></a>
        </c:forEach>
</div>
</ul>
<div id="footer">
<hr/>
    <c:if test="${myGroup == group}">
<button id="add-news" onclick="document.location.href='/showAddNewsForm?groupName=${group}'">Добавить новость</button>
    </c:if>
</div>
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