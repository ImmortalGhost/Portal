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
<a href="#"><-���������</a>
</div>
</div>
<div id="top">
<label>�������1</label>
<hr/>
</div>
<div id="center">
<ul>
<div class="user-choice">
<input type="radio" name="group1">��������1</input>
</div>
<div class="message">
<p>��������� �����weqweweqeweqwewewqeqweeqeqweqwewepkrwpe,rlwwqpekqwpekqwkeopqwkeoqwkeqowewkopqkeoqowkdoq kwodkqweqjw eijqwe qowew,,w;el, e ,el;r wkel; rwe;lrwek rkw;erw </p>
</div>
<div class="user-choice">
<input type="radio" name="group1">��������2</input>
</div>
<div class="message">
<p>��������� �����weqweweqeweqwewewqeqweeqeqweqwewepkrwpe,rlwwqpekqwpekqwkeopqwkeoqwkeqowewkopqkeoqowkdoq kwodkqweqjw eijqwe qowew,,w;el, e ,el;r wkel; rwe;lrwek rkw;erw </p>
</div>
<div class="user-choice">
<input type="radio" name="group1">��������2</input>
</div>
<div class="message">
<p>��������� �����weqweweqeweqwewewqeqweeqeqweqwewepkrwpe,rlwwqpekqwpekqwkeopqwkeoqwkeqowewkopqkeoqowkdoq kwodkqweqjw eijqwe qowew,,w;el, e ,el;r wkel; rwe;lrwek rkw;erw </p>
</div>
<div class="user-choice">
<input type="radio" name="group1">��������2</input>
</div>
<div class="message">
<p>��������� �����weqweweqeweqwewewqeqweeqeqweqwewepkrwpe,rlwwqpekqwpekqwkeopqwkeoqwkeqowewkopqkeoqowkdoq kwodkqweqjw eijqwe qowew,,w;el, e ,el;r wkel; rwe;lrwek rkw;erw </p>
</div>
</ul>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<div id="footer">
<hr/>
<form class="add-message">
<textarea name="message"></textarea>
<br/>
<input type="submit" value="��������"></input>
</form>
</div>
</div>
<div id="right">
<ul>
<div class="header">
<li><label id="name">���</label><label id="lastName">�������</label>
</div>
<br/>
<li><a href="#">��� ���������</a>
<li><a href="#">����������</a>
<li><a href="#">��������������</a>
<li><a href="#">������������� �������</a>
<li><a href="#">������� �������</a>
<li><a href="#">������������� ����������</a>
<li><a href="#">������� ����������</a>
<li><a href="#">�����</a>
</ul>
</div>
<body>
</html>