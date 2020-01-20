<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false"%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="UTF-8" />
    <title><spring:message code="label.addSpouseHeader" /></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
  </head>
  <body>
  <jsp:useBean id="date" class="java.util.Date"/>
    <h1><spring:message code="label.addSpouseHeader" /></h1>
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
        <c:forEach  items="${spouses}" var ="spouse">
        <tr>
          <td>${spouse.id}</td>
          <td><a href="${pageContext.request.contextPath}/${locale}/web-api/person/${personId}/add-spouse/${spouse.id}">${spouse.name}</a></td>
          <td><c:choose>
                        <c:when test="${spouse.sex == 'male'}">
                        <spring:message code="label.male" />
                        </c:when>
                        <c:otherwise>
                        <spring:message code="label.female" />
                        </c:otherwise>
                        </c:choose></td>
          <td><fmt:formatDate value="${spouse.birthDate}" type="date" pattern="dd-MM-yyyy"/></td>
          <td><fmt:formatDate value="${spouse.deathDate}" type="date" pattern="dd-MM-yyyy"/></td>
          <td>${spouse.biography}</td>
        </tr>
        </c:forEach>
      </table>
    </div>
    <br></br>
    <hr width = "500" align = left noshade>
    <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${personId}">
            <i><spring:message code="label.returnToPersonData" /><i>
        </a>
  </body>

</html>