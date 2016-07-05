<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="utf-8" %>
<html>
<head>
    <spring:url value="/resources/style.css" var="coreCss" />
    <link href="${coreCss}" rel="stylesheet" />
<style>
#center-form{
height: 30%;
}
#buttons-information{
margin-top: 90px;
}
</style>
</head>
<body>
<form:form id="center-form" method="post" commandName="user" action="/">
<div class="header">
<label>Вход в систему</label>
</div>
<br/>
<div class="table-left">
<label>Логин</label>
<br/>
<label>Пароль</label>
</div>
<div class="table-right">
<input name="login" type="text"></input>
<br/>
<input name="password" type="password"></input>
</div>
<div id="buttons-information">
<button type="submit">Войти</button>
</div>
</form:form>
</div>
</body>
</html>