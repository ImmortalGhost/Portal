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
<label>Группы</label>
</div>
<hr/>
</div>
<div id="left-down">
<ul>
    <c:forEach items="${groups}" var="group">
<li><a href="/teacherSelectsGroup?groupName=${group}">${group}</a>
        </c:forEach>
</ul>
</div>
<div id="right">
<ul>
<div class="header">
<li><label id="name"><c:out value="${name}"></c:out></label><label id="lastName"><c:out value="${lastName}"></c:out></label>
</div>
<br/>
<li><a href="/showBuddyList">Мои сообщения</a>
<li><a href="/informationTeacher">Информация</a>
<li><a href="/editTeacher">Редактирование</a>
<li><a href="/logOut">Выход</a></li>
</ul>
</div>
</body>
</html>