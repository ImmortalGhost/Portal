<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%response.setContentType("text/html;charset=utf-8");%>
<%request.setCharacterEncoding("utf-8");%>
<%@page pageEncoding="windows-1251" contentType="text/html; charset=windows-1251"%>
<html>
<head>
    <spring:url value="/resources/style.css" var="coreCss" />
    <link href="${coreCss}" rel="stylesheet" />
<style>
#buttons-create{
margin-top: 200px;
}
#big-center-form{
height: 70%;
}
</style>
</head>
<body>
<div id="big-center-form">
<div class="header">
<label>Обязательные поля</label>
</div>
<form:form method="post" commandName="somethinguserinfo" action="/create">
<div class="table-left">
<label>Логин</label>
<br/>
<label>Пароль</label>
<br/>
<label>Роль</label>
</div>
<div class="table-right">
<input type="text" name="login"/>
<br/>
<input type="password" name="password"/>
<br/>
<select name="selectedRole">
        <option disabled>Выберите роль</option>
        <c:forEach items="${roles}" var="role">
            <option value="<c:out value="${role}"/>"><c:out value="${role}"></c:out></option>
        </c:forEach>
</select>
</div>
<br/>
<br/>
<br/>
<div class="header">
<label>Необязательные поля</label>
</div>
<div class="table-left">
<label>Имя</label>
<br/>
<label>Фамилия</label>
<br/>
<label>Год рождения</label>
<br/>
<label>Группа(для студента)</label>
<br/>
<label>Должность(для преподавателя)</label>
</div>
<div class="table-right">
<input type="text" name="name"/>
<br/>
<input type="text" name="lastName"/>
<br/>
<input type="date" name="age"/>
<br/>
<select name="selectedGroup">
        <option disabled>Выберите группу</option>
        <c:forEach items="${groups}" var="group">
            <option value="<c:out value="${group}"/>"><c:out value="${group}"></c:out></option>
        </c:forEach>
</select>
<br/>
<select name="selectedPost">
        <option disabled>Выберите должность</option>
        <c:forEach items="${posts}" var="post">
            <option value="<c:out value="${post}"/>"><c:out value="${post}"></c:out></option>
        </c:forEach>
</select>
<br/>
</div>
<div id="buttons-create">
<input type="submit" value="Добавить"></input>
<input type="button" onclick="window.history.back();" value="Назад"></input>
</div>
</form:form>
</div>
</body>
</html>