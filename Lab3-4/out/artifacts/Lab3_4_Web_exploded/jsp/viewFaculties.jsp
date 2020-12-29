<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>

<html lang="${sessionScope.language}">
<head>
    <title>View Faculties</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style><%@include file="/css/forms.css" %></style>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/viewFaculties" class="applicationForm">
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <div class="fadeIn first">
                <img src="https://www.qbrobotics.com/wp-content/uploads/2018/03/sample-logo-470x235.png" id="icon" alt="User Icon" />
            </div>
            <span class="error_message" id="error">${requestScope.get('error')}</span>
            <p></p>
            <form class="page-form" action="${pageContext.request.contextPath}/viewFaculties" method="get">
        <span class = "page-form">
            <span>
              <label for="Alphabetical"><fmt:message key="viewFaculties.Alpabetical"/>: </label>
                 <select class="btn btn-info dropdown-toggle" name="Alphabetical" id="Alphabetical">
                    <c:choose>
                        <c:when test="${param.Alphabetical =='A-Z'}">
                            <option value="2" selected><fmt:message key="viewFaculties.A-Z"/></option>
                            <option value="3"><fmt:message key="viewFaculties.Z-A"/></option>
                            <option value="none"><fmt:message key="viewFaculties.None"/></option>
                        </c:when>
                        <c:when test="${param.price=='Z-A'}">
                            <option value="2"><fmt:message key="viewFaculties.A-Z"/></option>
                            <option value="3" selected><fmt:message key="viewFaculties.Z-A"/></option>
                            <option value="none"><fmt:message key="viewFaculties.None"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="none" selected><fmt:message key="viewFaculties.None"/></option>
                            <option value="2"><fmt:message key="viewFaculties.A-Z"/></option>
                            <option value="3"><fmt:message key="viewFaculties.Z-A"/></option>
                        </c:otherwise>
                    </c:choose>
                </select></span>
            <p></p>
            <span>  <label for="Budget">By budget places: </label>
                <select class="btn btn-info dropdown-toggle" name="Budget" id="Budget">
                    <c:choose>
                        <c:when test="${param.Budget=='Asc'}">
                            <option value="4" selected><fmt:message key="viewFaculties.Ascending"/></option>
                            <option value="5"><fmt:message key="viewFaculties.Descending"/></option>
                            <option value="none"><fmt:message key="viewFaculties.None"/></option>
                        </c:when>
                        <c:when test="${param.Budget=='Desc'}">
                            <option value="4"><fmt:message key="viewFaculties.Ascending"/></option>
                            <option value="5" selected><fmt:message key="viewFaculties.Descending"/></option>
                            <option value="none"><fmt:message key="viewFaculties.None"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="none" selected><fmt:message key="viewFaculties.None"/></option>
                            <option value="4"><fmt:message key="viewFaculties.Ascending"/></option>
                            <option value="5"><fmt:message key="viewFaculties.Descending"/></option>
                        </c:otherwise>
                    </c:choose>
                </select></span>
                <p></p>
               <span>  <label for="Places">By amount of places: </label>
                <select class="btn btn-info dropdown-toggle" name="Places" id="Places">
                    <c:choose>
                        <c:when test="${param.Places=='Asc'}">
                            <option value="6" selected><fmt:message key="viewFaculties.Ascending"/></option>
                            <option value="7"><fmt:message key="viewFaculties.Descending"/></option>
                            <option value="none"><fmt:message key="viewFaculties.None"/></option>
                        </c:when>
                        <c:when test="${param.Places=='Desc'}">
                            <option value="6"><fmt:message key="viewFaculties.Ascending"/></option>
                            <option value="7" selected><fmt:message key="viewFaculties.Descending"/></option>
                            <option value="none"><fmt:message key="viewFaculties.None"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="none" selected><fmt:message key="viewFaculties.None"/></option>
                            <option value="6"><fmt:message key="viewFaculties.Ascending"/></option>
                            <option value="7"><fmt:message key="viewFaculties.Descending"/></option>
                        </c:otherwise>
                    </c:choose>
                </select></span>
            <p></p>
             <button type="submit" class="btn btn-success btn-lg "><fmt:message key="viewFaculties.Sort"/></button>
        </span>
            </form>

            <form>
                <div class="result">
                    <div class="table">
                        <table class="table table-striped table-bordered table-sm">
                            <tr>
                                <th>№</th>
                                <th><fmt:message key="viewFaculties.Name"/></th>
                                <th><fmt:message key="viewFaculties.BudgetPlacesAmount"/></th>
                                <th><fmt:message key="viewFaculties.PlacesAmount"/></th>
                            </tr>
                            <c:set var="faculties" value="${requestScope.get('faculties')}"/>
                            <c:forEach var="faculty" items="${requestScope.get('faculties')}" >
                                <tr>
                                    <td>${faculty.getId()}</td>
                                    <td>${faculty.getName()}</td>
                                    <td>${faculty.getBudgetPlacesAmount()}</td>
                                    <td>${faculty.getPlacesAmount()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <c:set var="href" value="viewFaculties?" scope="request"/>
                        <jsp:include page="../jsp/pagination.jsp"/>
                    </div>
                </div>
            </form>
            <div class="form-group">
                <button type="submit" class="fadeIn fourth" value="Menu" style="background-color: #39ace7;">
                    <a method="post" href="${pageContext.request.contextPath}/menu"><fmt:message key="Menu"/></a>
                </button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
