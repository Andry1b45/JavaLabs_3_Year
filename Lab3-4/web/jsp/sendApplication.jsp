<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>

<html lang="${sessionScope.language}">
<head>
    <title>Send application</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style><%@include file="/css/forms.css" %></style>
</head>

<body>
<form method="post" action="${pageContext.request.contextPath}/sendApplication" class="applicationForm">
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <div class="fadeIn first">
                <img src="https://www.qbrobotics.com/wp-content/uploads/2018/03/sample-logo-470x235.png" id="icon" alt="User Icon" />
            </div>
            <span class="error_message" id="error">${requestScope.get('error')}</span>
            <p></p>

            <form>
                <input type="text" id="faculty" class="fadeIn first" name="faculty" placeholder="<fmt:message key="viewApplications.FacultyName"/>" required>
                <input type="text" id="math" class="fadeIn second" name="math" placeholder="<fmt:message key="viewApplications.MathMark"/>" required>
                <input type="text" id="ukrainian" class="fadeIn second" name="ukrainian" placeholder="<fmt:message key="viewApplications.UkrainianMark"/>" required>
                <input type="text" id="english" class="fadeIn third" name="english" placeholder="<fmt:message key="viewApplications.EnglishMark"/>" required>
                <input type="text" id="history" class="fadeIn third" name="history" placeholder="<fmt:message key="viewApplications.HistoryMark"/>" required>
                <input type="submit" class="fadeIn fourth" value="<fmt:message key="Send"/>">
            </form>
            <div class="form-group">
                <button type="submit" class="fadeIn fourth" value="Faculties" style="background-color: #39ace7;">
                    <a method="post" href="${pageContext.request.contextPath}/jsp/viewFaculties.jsp"><fmt:message key="studentMenu.ViewFacultes"/></a>
                </button>
                <button type="submit" class="fadeIn fourth" value="Menu" style="background-color: #39ace7;">
                    <a method="post" href="${pageContext.request.contextPath}/jsp/studentMenu.jsp"><fmt:message key="Menu"/></a>
                </button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
