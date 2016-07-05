<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="utf-8" %>
<html>
<head>
    <spring:url value="/resources/style.css" var="coreCss" />
    <link href="${coreCss}" rel="stylesheet" />
    <style>
        #text{
            margin-left: 12%;
            position: absolute;
            margin-top: -90px;
        }
        #buttons{
            margin-left: 140px;
            margin-top: 120px;
        }
    </style>
</head>
<body>
<div id="center-form">
<div class="header">
<label>Название группы</label>
</div>
    <form:form action="/renameGroup" method="post">
        <input id="text" type="text" name="groupName" value="<c:out value="${oldGroupName}"/>"/>
        <input id="text" type="hidden" name="oldGroupName" value="<c:out value="${oldGroupName}"/>"/>
    <div id="buttons">
<input type="submit" value="Переименовать"/>
<input type="button" onclick="window.history.back();" value="Назад"></input>
</div>
    </form:form>
</div>
</body>
</html>