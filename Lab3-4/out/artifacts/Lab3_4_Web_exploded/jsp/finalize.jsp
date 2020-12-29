<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>

<html lang="${sessionScope.language}">
<head>
    <title>Finalize</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style><%@include file="/css/forms.css" %></style>
</head>

<body>
<form method="post" action="${pageContext.request.contextPath}/finalize" class="finalize_form">
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <div class="fadeIn first">
                <img src="https://www.qbrobotics.com/wp-content/uploads/2018/03/sample-logo-470x235.png" id="icon" alt="User Icon" />
            </div>
            <form>
                <input type="text" id="faculty" class="fadeIn first" name="facultyName"
                       placeholder="<fmt:message key="finalize.FacultyName"/>" required>
                <input type="submit" class="fadeIn second" value="<fmt:message key="Send"/>">
            </form>
            <div class="form-group">
                <button type="submit" class="fadeIn fourth" value="<fmt:message key="adminMenu.Applications"/>" style="background-color: #39ace7;">
                    <a method="post" href="${pageContext.request.contextPath}/viewApplications"><fmt:message key="adminMenu.Applications"/></a>
                </button>
            </div>
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
