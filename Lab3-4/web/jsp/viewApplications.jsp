<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>

<html lang="${sessionScope.language}">
<head>
    <title>View Applications</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style><%@include file="/css/forms.css" %></style>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/viewApplications" class="applicationForm">
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <div class="fadeIn first">
                <img src="https://www.qbrobotics.com/wp-content/uploads/2018/03/sample-logo-470x235.png" id="icon" alt="User Icon" />
            </div>
            <form>
                <div class="result">
                    <div class="table">
                        <table class="table table-striped table-bordered table-sm">
                            <tr>
                                <th><fmt:message key="viewApplications.FacultyName"/></th>
                                <th><fmt:message key="viewApplications.StudentName"/></th>
                                <th><fmt:message key="viewApplications.MathMark"/></th>
                                <th><fmt:message key="viewApplications.UkrainianMark"/></th>
                                <th><fmt:message key="viewApplications.EnglishMark"/></th>
                                <th><fmt:message key="viewApplications.HistoryMark"/></th>
                            </tr>
                            <c:set var="applications" value="${requestScope.get('applications')}"/>
                            <c:forEach var="application" items="${requestScope.get('applications')}" >
                                <tr>
                                    <td>${application.getFacultyName()}</td>
                                    <td>${application.getStudentUsername()}</td>
                                    <td>${application.getMathGrade()}</td>
                                    <td>${application.getUkrainianGrade()}</td>
                                    <td>${application.getEnglishGrade()}</td>
                                    <td>${application.getHistoryGrade()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <c:set var="href" value="viewApplications?" scope="request"/>
                        <jsp:include page="../jsp/pagination.jsp"/>
                    </div>
                </div>
            </form>
            <div class="form-group">
                <button type="submit" class="fadeIn fourth" value="Menu" style="background-color: #39ace7;">
                    <a method="post" href="${pageContext.request.contextPath}/jsp/adminMenu.jsp"><fmt:message key="Menu"/></a>
                </button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
