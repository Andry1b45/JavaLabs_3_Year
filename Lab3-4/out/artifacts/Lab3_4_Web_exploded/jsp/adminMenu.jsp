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
<form method="post" action="${pageContext.request.contextPath}/jsp/adminMenu.jsp" class="adminMenuForm">
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <div class="fadeIn first">
                <img src="https://www.qbrobotics.com/wp-content/uploads/2018/03/sample-logo-470x235.png" id="icon" alt="User Icon" />
            </div>

            <form>
                <div class="form-group">
                    <button type="submit" class="fadeIn first" value="Faculties" style="background-color: #39ace7;">
                        <a method="post" href="${pageContext.request.contextPath}/jsp/viewFaculties.jsp"><fmt:message key="adminMenu.ViewFaculties"/></a>
                    </button>
                </div>

                <div class="form-group">
                    <button type="submit" class="fadeIn first" value="Edit faculty" style="background-color: #39ace7;">
                        <a method="post" href="${pageContext.request.contextPath}/jsp/editFaculty.jsp"><fmt:message key="adminMenu.CreateEditFaculty"/></a>
                    </button>
                </div>

                <div class="form-group">
                    <button type="submit" class="fadeIn second" value="Remove faculty" style="background-color: #39ace7;">
                        <a method="post" href="${pageContext.request.contextPath}/jsp/removeFaculty.jsp"><fmt:message key="adminMenu.RemoveFaculty"/></a>
                    </button>
                </div>

                <div class="form-group">
                    <button type="submit" class="fadeIn second" value="View Students" style="background-color: #39ace7;">
                        <a method="post" href="${pageContext.request.contextPath}/viewStudents"><fmt:message key="adminMenu.ViewStudents"/></a>
                    </button>
                </div>

                <div class="form-group">
                    <button type="submit" class="fadeIn third" value="Block Student" style="background-color: #39ace7;">
                        <a method="post" href="${pageContext.request.contextPath}/jsp/blockStudent.jsp"><fmt:message key="adminMenu.BlockUnblockStudent"/></a>
                    </button>
                </div>

                <div class="form-group">
                    <button type="submit" class="fadeIn third" value="New Results" style="background-color: #39ace7;">
                        <a method="post" href="${pageContext.request.contextPath}/jsp/addResults.jsp"><fmt:message key="adminMenu.AddResults"/></a>
                    </button>
                </div>

                <div class="form-group">
                    <button type="submit" class="fadeIn fourth" value="ShowApplications" style="background-color: #39ace7;">
                        <a method="post" href="${pageContext.request.contextPath}/viewApplications"><fmt:message key="adminMenu.Applications"/></a>
                    </button>
                </div>

                <div class="form-group">
                    <button type="submit" class="fadeIn fourth" value="Finalize" style="background-color: #39ace7;">
                        <a method="post" href="${pageContext.request.contextPath}/jsp/finalize.jsp"><fmt:message key="adminMenu.Finalize"/></a>
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
