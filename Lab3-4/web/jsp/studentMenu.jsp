<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>

<html lang="${sessionScope.language}">
<head>
    <title>Login</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style><%@include file="/css/forms.css" %></style>
</head>

<body>
<form method="post" action="${pageContext.request.contextPath}/jsp/studentMenu.jsp" class="studentMenuForm">
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <div class="fadeIn first">
                <img src="https://www.qbrobotics.com/wp-content/uploads/2018/03/sample-logo-470x235.png" id="icon" alt="User Icon" />
            </div>

            <form>
                <div class="form-group">
                    <button type="submit" class="fadeIn first" value="View faculties" style="background-color: #39ace7;">
                        <a method="post" href="${pageContext.request.contextPath}/jsp/viewFaculties.jsp"><fmt:message key="studentMenu.ViewFacultes"/></a>
                    </button>
                </div>

                <div class="form-group">
                    <button type="submit" class="fadeIn second" value="Send application" style="background-color: #39ace7;">
                        <a method="get" href="${pageContext.request.contextPath}/jsp/sendApplication.jsp"><fmt:message key="studentMenu.SendApplication"/></a>
                    </button>
                </div>

                <div class="form-group">
                    <button type="submit" class="fadeIn fourth" value="Logout" style="background-color: #39ace7;">
                        <a method="post" href="${pageContext.request.contextPath}/logout"><fmt:message key="Logout"/></a>
                    </button>
                </div>

            </form>


        </div>
    </div>
</form>
</body>
</html>
