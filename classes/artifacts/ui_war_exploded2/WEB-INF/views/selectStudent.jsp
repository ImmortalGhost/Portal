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
    <c:choose>
        <c:when test="${selectedRole == 'Student'}">
            <label>Информация о студенте</label>
        </c:when>
        <c:when test="${selectedRole == 'Teacher'}">
            <label>Информация о преподавателе</label>
        </c:when>
    </c:choose>
</div>
<br/>
<div class="table-left">
<label>Имя</label>
<br/>
<label>Фамилия</label>
<br/>
<label>Год рождения</label>
<br/>
    <c:choose>
        <c:when test="${selectedRole == 'Student'}">
            <label>Группа</label>
        </c:when>
        <c:when test="${selectedRole == 'Teacher'}">
            <label>Должность</label>
        </c:when>
    </c:choose>
</div>
<div class="table-right">
<label><c:out value="${name}"/></label>
<br/>
<label><c:out value="${lastName}"/></label>
<br/>
<label><c:out value="${age}"/></label>
<br/>
    <c:choose>
        <c:when test="${selectedRole == 'Student'}">
            <label><c:out value="${group}"/></label>
        </c:when>
        <c:when test="${selectedRole == 'Teacher'}">
            <label><c:out value="${post}"/></label>
        </c:when>
    </c:choose>
</div>
<div id="buttons-create">
<input type="button" onclick="document.location.href='/showChat?fullName=${name}' + ' ' + '${lastName}'" value="Отправить сообщение"></input>
    <input type="button" onclick="window.history.back();" value="Назад"></input>
</div>
</div>
</body>
</html>