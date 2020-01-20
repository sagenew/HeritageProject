<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false"%>
<html>
<head>
<meta charset="UTF-8" />
<title>spring:message code="label.updatePersonHeader" /></title>
</head>
<body>

<h2><spring:message code="label.updatePersonHeader" /></h2>
<form:form method="post"
action="${pageContext.request.contextPath}/${locale}/web-api/person-list/update-person/${personId}"
modelAttribute="personForm">
<table border="1">
<tr>
<td><form:label path="name">
<spring:message code="label.name" />
</form:label></td>
<td><form:input path="name" value = "${personName}"/></td>
</tr>
<tr>
<td><form:label path="sex">
<spring:message code="label.sex" />
</form:label></td>
<td>
<c:choose>
        <c:when test="${personSex == 'male'}">
            <form:radiobutton path="sex" value="male"   checked = "checked"/>
            <spring:message code="label.male" />
            <form:radiobutton path="sex" value="female" />
            <spring:message code="label.female" />
        </c:when>
<c:otherwise>
<form:radiobutton path="sex" value="male"   />
<spring:message code="label.male" />
<form:radiobutton path="sex" value="female" checked = "checked"/>
<spring:message code="label.female" />
</c:otherwise>
</c:choose>
</td>
</tr>
<tr>
<td><form:label path="birthDate" >
<spring:message code="label.birthDate" />
</form:label></td>
<td><form:input type="date" path="birthDate" value = "${personBirthDate}"/></td>
</tr>
<tr>
<td><form:label path="deathDate">
<spring:message code="label.deathDate" />
</form:label></td>
<td><form:input type="date" path="deathDate" value = "${personDeathDate}"/></td>
</tr>
<tr>
<td><form:label path="biography">
<spring:message code="label.biography" />
</form:label></td>
<td><form:input path="biography" value ="${personBiography}" /></td>
</tr>
</table>
<spring:message code="label.submit" var="labelSubmit"></spring:message>
<input type="submit" value="${labelSubmit}"/>
</form:form>
<br></br>
<hr width = "450" align = left noshade>
<a href="${pageContext.request.contextPath}/${locale}/web-api/person-list">
<spring:message code="label.returnToPersonList" />
</a>
</body>
</html>