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
</head>
<body>
<div id="left">
<div id="back">
<a href="/homeUser"><-Вернуться</a>
<br/>
<a href="">Информация о собеседнике</a>
</div>
</div>
<div id="top">
<label id="namePerson"><c:out value="${nameTo} ${lastNameTo}"/></label>
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
<div id="footer">
<hr/>
    <form:form class="add-message" method="post" action="/sendMessage">
<textarea name="text"></textarea>
        <input type="hidden" name="name" value="${nameTo}"/>
        <input type="hidden" name="lastName" value="${lastNameTo}"/>
<br/>
<input type="submit" value="Написать"></input>
</form:form>
</div>
</div>
<div id="right">
<ul>
<div class="header">
<li><label id="name"><c:out value="${nameFrom}"/></label><label id="lastName"><c:out value="${lastNameFrom}"/></label>
</div>
<br/>
<li><a href="#">Мои сообщения</a>
<li><a href="/informationStudent">Информация</a>
<li><a href="/editStudent">Редактирование</a>
<li><a href="/logOut">Выход</a>
</ul>
</div>
</body>
</html>