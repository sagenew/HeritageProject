<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false"%>
<html>
<head>
<meta charset="UTF-8" />
<title>spring:message code="label.addPersonHeader" /></title>
</head>
<body>

<h2><spring:message code="label.addPersonHeader" /></h2>
<form:form method="post"
action="${pageContext.request.contextPath}/${locale}/web-api/person-list/add-person"
modelAttribute="personForm">
<table border="1">
<tr>
<td><form:label path="name">
<spring:message code="label.name" />
</form:label></td>
<td><form:input path="name"/></td>
</tr>
<tr>
<td><form:label path="sex">
<spring:message code="label.sex" />
</form:label></td>
<td>
<form:radiobutton path="sex" value="male"   />
<spring:message code="label.male" />
<form:radiobutton path="sex" value="female" />
<spring:message code="label.female" />
</td>
</tr>
<tr>
<td><form:label path="birthDate" >
<spring:message code="label.birthDate" />
</form:label></td>
<td><form:input type="date" path="birthDate"/></td>
</tr>
<tr>
<td><form:label path="deathDate">
<spring:message code="label.deathDate" />
</form:label>
</td>
<td><form:input type="date" path="deathDate"/></td>
</tr>
<tr>
<td><form:label path="biography">
<spring:message code="label.biography" />
</form:label></td>
<td><form:textarea path="biography" rows="3" cols="30"/></td>
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