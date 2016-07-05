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
<div id="big-center-form">
<div class="header">
<label>�������������� ����������</label>
</div>
    <form:form method="post" commandName="studentinfo" action="/actionStudent">
<div class="table-left">
<label>���</label>
<br/>
<label>�������</label>
<br/>
<label>��� ��������</label>
<br/>
<label>������</label>
<br/>
<label>�����</label>
<br/>
<label>������</label>
</div>
<div class="table-right">
<input type="text" name="name" value="<c:out value="${name}"/>"/>
<br/>
<input type="text" name="lastName" value="<c:out value="${lastName}"/>"/>
<br/>
<input type="date" name="age" value="<c:out value="${age}"/>"/>
<br/>
<select name="selectedGroup">
<option disabled>�������� ������</option>
    <c:forEach items="${groups}" var="group">
        <c:choose>
        <c:when test="${group == myGroup}">
            <option selected="selected" value="<c:out value="${group}"></c:out>"><c:out value="${group}"></c:out></option>
        </c:when>
            <c:otherwise>
                <option value="<c:out value="${group}"></c:out>"><c:out value="${group}"></c:out></option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</select>
<br/>
<input type="text" readonly="readonly" name="login" value="<c:out value="${login}"/>"/>
<br/>
<input type="text" readonly="readonly" name="password" value="<c:out value="${password}"/>"/>
</div>
<br/>
<div id="buttons">
    <br/>
<input type="submit" value="�������������"></input>
<input type="button" onclick="document.location.href='/back'" value="�����"></input>
</div>
</form:form>
</div>
</body>
</html>