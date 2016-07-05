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
<div id="center-form">
<div class="header">
<label>Информация о пользователе</label>
</div>
<br/>
<div class="table-left">
<label>Имя</label>
<br/>
<label>Фамилия</label>
<br/>
<label>Год рождения</label>
<br/>
<label>Группа</label>
</div>
<div class="table-right">
<label><c:out value="${name}"/></label>
<br/>
<label><c:out value="${lastName}"/></label>
<br/>
<label><c:out value="${age}"/></label>
<br/>
<label><c:out value="${group}"/></label>
</div>
<div id="buttons-information">
<button onclick="document.location.href='/homeUser'">Назад</button>
</div>
</div>
</body>
</html>