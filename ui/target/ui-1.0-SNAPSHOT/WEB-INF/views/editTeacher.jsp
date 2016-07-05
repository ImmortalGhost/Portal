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
#big-center-form{
height: 55%;
}
</style>
</head>
<body>
<div id="big-center-form">
<div class="header">
<label>Редактирование информации</label>
</div>
    <form:form method="post" commandName="teacherinfo" action="/actionTeacher">
<div class="table-left">
<label>Имя</label>
<br/>
<label>Фамилия</label>
<br/>
<label>Год рождения</label>
<br/>
<label>Логин</label>
<br/>
<label>Пароль</label>
<br/>
<label>Должность</label>
</div>
<div class="table-right">
<input type="text" name="name" value="<c:out value="${name}"/>"/>
<br/>
<input type="text" name="lastName" value="<c:out value="${lastName}"/>"/>
<br/>
<input type="date" name="age" value="<c:out value="${age}"/>"/>
<br/>
<input type="text" readonly="readonly" name="login" value="<c:out value="${login}"/>"/>
<br/>
<input type="text" readonly="readonly" name="password" value="<c:out value="${password}"/>"/>
<br/>
    <select name="selectedPost">
        <option disabled>Выберите должность</option>
        <c:forEach items="${posts}" var="post">
            <c:choose>
                <c:when test="${post == myPost}">
                    <option selected="selected" value="<c:out value="${post}"></c:out>"><c:out value="${post}"></c:out></option>
                </c:when>
                <c:otherwise>
                    <option value="<c:out value="${post}"></c:out>"><c:out value="${post}"></c:out></option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</div>
<br/>
<br/>
<div id="buttons">
<input type="submit" value="Редактировать"></input>
<input type="button" onclick="document.location.href='/back'" value="Назад"></input>
</div>
</form:form>
</div>
</body>
</html>