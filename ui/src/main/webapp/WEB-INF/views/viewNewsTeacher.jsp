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
        <c:choose>
            <c:when test="${role == 'Student'}">
                <a href="#" onclick="window.history.back();"><-Вернуться</a>
            </c:when>
            <c:when test="${role == 'Teacher'}">
                <a href="#" onclick="window.history.back();"><-Вернуться</a>
            </c:when>
        </c:choose>
    </div>
</div>
<div id="top">
    <label><c:out value="${nameNews}"/></label>
    <hr/>
</div>
<div id="center">
    <ul>
        <c:forEach items="${texts}" var="thisText" varStatus="status">
            <label class="user">${names[status.index]}</label>
            <div class="message">
                <p>${thisText}</p>
            </div>
        </c:forEach>
    </ul>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div id="footer">
        <hr/>
        <form:form class="add-message" method="post" action="/addCommentToNews">
            <textarea name="text"></textarea>
            <input type="hidden" name="nameNews" value="${nameNews}"/>
            <input type="hidden" name="nameGroup" value="${nameGroup}"/>
            <br/>
            <input type="submit" value="Написать"></input>
        </form:form>
    </div>
</div>
<div id="right">
    <ul>
        <div class="header">
            <li><label id="name"><c:out value="${name}"/></label><label id="lastName"><c:out value="${lastName}"/></label>
        </div>
        <br/>
        <li><a href="/showBuddyList">Мои сообщения</a>
            <c:choose>
            <c:when test="${role == 'Student'}">
        <li><a href="/informationStudent">Информация</a>
        <li><a href="/editStudent">Редактирование</a>
            </c:when>
        <c:when test="${role == 'Teacher'}">
        <li><a href="/informationTeacher">Информация</a>
        <li><a href="/editTeacher">Редактирование</a>
            </c:when>
            </c:choose>
        <li><a href="/logOut">Выход</a>
    </ul>
</div>
</body>
</html>