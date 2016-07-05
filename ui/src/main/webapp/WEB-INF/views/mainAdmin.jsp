<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="utf-8" %>
<html>
<head>
    <spring:url value="/resources/style.css" var="coreCss" />
    <link href="${coreCss}" rel="stylesheet" />
    <script src="<c:url value="/resources/jquery.js"/>"></script>
</head>
<body>
<div id="top-left">
<div class="header">
<label>Группы</label>
</div>
<hr/>
</div>
</div>
<div id="left-down">
    <ul>
        <c:forEach items="${groups}" var="group">
        <li><a class="choiceGroup" href="<c:out value="/choice?name=${group}"/>">${group}</a>
            </c:forEach>
    </ul>
</div>
<div id="top">
<label>Группа - <c:out value="${group}"></c:out></label>
    <input type="hidden" id="groupName" value="<c:out value="${group}"></c:out>"></input>
<hr/>
</div>
<div id="center-down">
<form>
<div class="header">
<label>Студенты</label>
</div>
<ul>
    <c:forEach items="${students}" var="student">
<li><input type="radio" name="group1" value="<c:out value="${student}"/>"><c:out value="${student}"></c:out></input>
        </c:forEach>
</ul>
<div class="header">
<label>Преподаватели</label>
</div>
<ul>
    <c:forEach items="${teachers}" var="teacher">
<li><input type="radio" name="group1" value="<c:out value="${teacher}"/>"><c:out value="${teacher}"></c:out></input>
    </c:forEach>
</ul>
</form>
</div>
<div id="right">
<ul>
<div class="header">
<li><label>Меню</label>
</div>
<br/>
<li><a href="/create">Добавить нового пользователя</a>
<li><a id="edit" href="#">Редактировать пользователя</a>
<li><a id="delete" href="#">Удалить пользователя</a>
<li><a href="/addGroup">Добавить новую группу</a>
<li><a id="renameLink" href="#">Переименовать выбранную группу</a>
<li><a id="deleteLink" href="#">Удалить выбранную группу</a>
<li><a href="/logOut">Выход</a></li>
    </ul>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $("input:radio").click(function(){
            var element = $(this);
            var name = element.val();
            var group = $("#groupName");
            $("#edit").attr("href", "/action?group=" + group.val() + "&name=" + name + "&task=edit");
            $("#delete").attr("href", "/action?group=" + group.val() + "&name=" + name + "&task=delete");
        });
        $("#renameLink").click(function(){
            var group = $("#groupName");
            $(location).attr("href", "/renameGroup?oldGroupName=" + group.val());
        });
        $("#deleteLink").click(function(){
            var group = $("#groupName");
            $(location).attr("href", "/deleteGroup?groupName=" + group.val());
        });
    });
</script>
</body>
</html>