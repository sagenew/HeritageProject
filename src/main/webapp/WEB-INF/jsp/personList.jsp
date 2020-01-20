<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false"%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="UTF-8" />
    <title><spring:message    code="label.personListHeader" /></title>
  </head>
  <body>
  <jsp:useBean id="date" class="java.util.Date"/>
  <div style="text-align: left;padding:5px;margin:5px 1300px 5px 5px;background:#FFFF99;">
         <a href="${pageContext.request.contextPath}/en/web-api/person-list"><i style="font-size:120%; ">English</i></a>
         &nbsp;|&nbsp;
         <a href="${pageContext.request.contextPath}/ua/web-api/person-list"><i style="font-size:120%; ">Українська</i></a>
  </div>
    <h1><spring:message code="label.personListHeader" /></h1>
    <c:choose>
        <c:when test="${persons.isEmpty()}">
            <i><p style="font-size:120%; "><spring:message    code="label.noPersons" /></p></i>
        </c:when>
    <c:otherwise>
    <div>
      <table border="1">
        <tr>
            <th><spring:message code="label.id" /></th>
            <th><spring:message code="label.name" /></th>
            <th><spring:message code="label.sex" /></th>
            <th><spring:message code="label.birthDate" /></th>
            <th><spring:message code="label.deathDate" /></th>
            <th><spring:message code="label.biography" /></th>
        </tr>
        <c:forEach  items="${persons}" var ="person">
        <tr>
          <td>${person.id}</td>
          <td><a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}">${person.name}</a></td>
          <td>
          <c:choose>
          <c:when test="${person.sex == 'male'}">
          <spring:message code="label.male" />
          </c:when>
          <c:otherwise>
          <spring:message code="label.female" />
          </c:otherwise>
          </c:choose>
          </td>
          <td><fmt:formatDate value="${person.birthDate}" type="date" pattern="dd-MM-yyyy"/></td>
          <td><fmt:formatDate value="${person.deathDate}" type="date" pattern="dd-MM-yyyy"/></td>
          <td>${person.biography}</td>
          <td>
              <a href="${pageContext.request.contextPath}/${locale}/web-api/person-list/update-person/${person.id}">
              <i><spring:message code="label.update" /><i>
              </a>
          </td>
          <td>
              <a href="${pageContext.request.contextPath}/${locale}/web-api/person-list/delete-person/${person.id}">
              <i><spring:message code="label.delete" /><i>
              </a>
          </td>
        </tr>
        </c:forEach>
      </table>
    </div>
    <br></br>
    </c:otherwise>
    </c:choose>
    <hr width = "450" align = left noshade>
    <a href="${pageContext.request.contextPath}/${locale}/web-api/person-list/add-person">
         <i><spring:message code="label.addPerson" /><i>
    </a>
  </body>

</html>