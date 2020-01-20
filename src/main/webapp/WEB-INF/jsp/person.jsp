<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false"%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="UTF-8" />
    <title><spring:message code="label.personHeader" /></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
  </head>
  <body>
  <jsp:useBean id="date" class="java.util.Date"/>
  <h1><spring:message code="label.personHeader" /></h1>
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
  <tr>
  <td>${person.id}</td>
  <td><a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}">${person.name}</a></td>
  <td><c:choose>
                <c:when test="${person.sex == 'male'}">
                <spring:message code="label.male" />
                </c:when>
                <c:otherwise>
                <spring:message code="label.female" />
                </c:otherwise>
                </c:choose></td>
  <td><fmt:formatDate value="${person.birthDate}" type="date" pattern="dd-MM-yyyy"/></td>
  <td><fmt:formatDate value="${person.deathDate}" type="date" pattern="dd-MM-yyyy"/></td>
  <td>${person.biography}</td>
  </a>
  </td>
  </tr>
  </table>
  </div>
  <br></br>
  <hr width = "450" align = left noshade>


  <h2><spring:message code="label.personSpouseHeader" /></h2>
  <c:choose>
    <c:when test="${spouse == null}">
        <i><p style="font-size:120%; ">${person.name} <spring:message code="label.personHasNoSpouse" /></p></i>
        <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/add-spouse"><i><spring:message code="label.personAddSpouse" /><i></a>
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
  <tr>
  <td>${spouse.id}</td>
  <td><a href="${pageContext.request.contextPath}/${locale}/web-api/person/${spouse.id}">${spouse.name}</a></td>
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
  <td>
    <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/delete-spouse">
    <i><spring:message code="label.deleteRelation" /><i>
    </a>
  </td>
  </tr>
  </table>
  </div>
  </c:otherwise>
  </c:choose>


  <h2><spring:message code="label.personParentsHeader" /></h2>
  <c:choose>
           <c:when test="${personParents.isEmpty()}">
               <i><p style="font-size:120%; ">${person.name} <spring:message code="label.personHasNoParents" /></p></i>
           </c:when>
   <c:otherwise>
   <table border="1">
   <tr>
     <th><spring:message code="label.id" /></th>
     <th><spring:message code="label.name" /></th>
     <th><spring:message code="label.sex" /></th>
     <th><spring:message code="label.birthDate" /></th>
     <th><spring:message code="label.deathDate" /></th>
     <th><spring:message code="label.biography" /></th>
   </tr>
   <c:forEach  items="${personParents}" var ="parent">
   <tr>
   <td>${parent.id}</td>
   <td><a href="${pageContext.request.contextPath}/${locale}/web-api/person/${parent.id}">${parent.name}</a></td>
   <td><c:choose>
                 <c:when test="${parent.sex == 'male'}">
                 <spring:message code="label.male" />
                 </c:when>
                 <c:otherwise>
                 <spring:message code="label.female" />
                 </c:otherwise>
                 </c:choose></td>
   <td><fmt:formatDate value="${parent.birthDate}" type="date" pattern="dd-MM-yyyy"/></td>
   <td><fmt:formatDate value="${parent.deathDate}" type="date" pattern="dd-MM-yyyy"/></td>
   <td>${parent.biography}</td>
   <td>
     <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/delete-parent/${parent.id}">
     <i><spring:message code="label.deleteRelation" /><i>
     </a>
   </td>
   </tr>
   </c:forEach>
   </table>
   </div>
   </c:otherwise>
   </c:choose>
   <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/add-parent"><i><spring:message code="label.personAddParent" /><i></a>

  <h2><spring:message code="label.personChildrenHeader" /></h2>
  <c:choose>
        <c:when test="${personChildren.isEmpty()}">
            <i><p style="font-size:120%; ">${person.name} <spring:message code="label.personHasNoChildren" /></p></i>
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
  <c:forEach  items="${personChildren}" var ="child">
  <tr>
  <td>${child.id}</td>
  <td><a href="${pageContext.request.contextPath}/${locale}/web-api/person/${child.id}">${child.name}</a></td>
  <td><c:choose>
                <c:when test="${child.sex == 'male'}">
                <spring:message code="label.male" />
                </c:when>
                <c:otherwise>
                <spring:message code="label.female" />
                </c:otherwise>
                </c:choose></td>
  <td><fmt:formatDate value="${child.birthDate}" type="date" pattern="dd-MM-yyyy"/></td>
  <td><fmt:formatDate value="${child.deathDate}" type="date" pattern="dd-MM-yyyy"/></td>
  <td>${child.biography}</td>
  <td>
    <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/delete-child/${child.id}">
    <i><spring:message code="label.deleteRelation" /><i>
    </a>
    </td>
  </tr>
  </c:forEach>
  </table>
  </div>
  </c:otherwise>
  </c:choose>
  <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/add-child"><i><spring:message code="label.personAddChild" /><i></a>

  <h2><spring:message code="label.personCousinsHeader" /></h2>
  <c:choose>
          <c:when test="${personCousins.isEmpty()}">
              <i><p style="font-size:120%; ">${person.name} <spring:message code="label.personHasNoCousins" /></p></i>
          </c:when>
  <c:otherwise>
  <table border="1">
  <tr>
    <th><spring:message code="label.id" /></th>
    <th><spring:message code="label.name" /></th>
    <th><spring:message code="label.sex" /></th>
    <th><spring:message code="label.birthDate" /></th>
    <th><spring:message code="label.deathDate" /></th>
    <th><spring:message code="label.biography" /></th>
  </tr>
  <c:forEach  items="${personCousins}" var ="cousin">
  <tr>
  <td>${cousin.id}</td>
  <td><a href="${pageContext.request.contextPath}/${locale}/web-api/person/${cousin.id}">${cousin.name}</a></td>
  <td><c:choose>
                <c:when test="${cousin.sex == 'male'}">
                <spring:message code="label.male" />
                </c:when>
                <c:otherwise>
                <spring:message code="label.female" />
                </c:otherwise>
                </c:choose></td>
  <td><fmt:formatDate value="${cousin.birthDate}" type="date" pattern="dd-MM-yyyy"/></td>
  <td><fmt:formatDate value="${cousin.deathDate}" type="date" pattern="dd-MM-yyyy"/></td>
  <td>${cousin.biography}</td>
  <td>
    <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/delete-cousin/${cousin.id}">
    <i><spring:message code="label.deleteRelation" /><i>
    </a>
  </td>
  </tr>
  </c:forEach>
  </table>
  </div>
  </c:otherwise>
  </c:choose>
  <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/add-cousin"><i><spring:message code="label.personAddCousin" /><i></a>
  <br></br>
  <hr width = "450" align = left noshade>
  <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/get-ancestors"><i><spring:message code="label.personShowAncestors" /><i></a>
  <br></br>
  <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/get-descendants"><i><spring:message code="label.personShowDescendants" /><i></a>
  <br></br>
  <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/get-relatives"><i><spring:message code="label.personShowRelatives" /><i></a>
  <br></br>
  <a href="${pageContext.request.contextPath}/${locale}/web-api/person/${person.id}/get-relation"><i><spring:message code="label.personFindRelations" /><i></a>
  <br></br>
  <hr width = "450" align = left noshade>
  <a href="${pageContext.request.contextPath}/${locale}/web-api/person-list"><spring:message code="label.returnToPersonList" /></a>
  </body>

</html>