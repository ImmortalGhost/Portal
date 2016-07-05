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
<label>Информация о студенте</label>
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
<div id="buttons-create">
<input type="button" onclick="document.location.href='/showChat?fullName=${name}' + ' ' + '${lastName}'" value="Отправить сообщение"></input>
<input type="button" onclick="document.location.href='/selectedGroup?groupName=${group}'" value="Назад"></input>
</div>
</div>
</body>
</html>