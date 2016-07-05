<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="utf-8" %>
<html>
<head>
    <spring:url value="/resources/style.css" var="coreCss" />
    <link href="${coreCss}" rel="stylesheet" />
    <style>
        .header label{
            margin-top: 100px;
            font-size: 30px;
        }
    </style>
</head>
<body>
<div id="center-form">
<div class="header">
    <label>403 - Ошибка доступа</label>
</div>
</div>
</body>
</html>