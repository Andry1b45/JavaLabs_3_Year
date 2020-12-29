<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>

<html lang="${sessionScope.language}">
<head>
    <title>Registration</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style><%@include file="/css/forms.css" %></style>
</head>

<body>
<form method="post" action="${pageContext.request.contextPath}/register" class="login_form">
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <div class="fadeIn first">
                <img src="https://www.qbrobotics.com/wp-content/uploads/2018/03/sample-logo-470x235.png" id="icon" alt="User Icon" />
            </div>
            <span class="error_message" id="error">${requestScope.get('error')}</span>
            <p></p>
            <form class="page-form" action="${pageContext.request.contextPath}/register" method="get">
                <span class = "page-form">
                    <span>
                      <label for="role"><fmt:message key="registration.Role"/></label>
                         <select class="btn btn-info dropdown-toggle" name="role" id="role">
                            <c:choose>
                                <c:when test="${param.role =='Student'}">
                                    <option value="1" selected><fmt:message key="registration.Student"/></option>
                                    <option value="2"><fmt:message key="registration.Admin"/></option>
                                    <option value="none"><fmt:message key="viewFaculties.None"/></option>
                                </c:when>
                                <c:when test="${param.role=='Admin'}">
                                    <option value="1"><fmt:message key="registration.Student"/></option>
                                    <option value="2" selected><fmt:message key="registration.Admin"/></option>
                                    <option value="none"><fmt:message key="viewFaculties.None"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="1" ><fmt:message key="registration.Student"/></option>
                                    <option value="2"><fmt:message key="registration.Admin"/></option>
                                    <option value="none" selected><fmt:message key="viewFaculties.None"/></option>
                                </c:otherwise>
                            </c:choose>
                        </select></span>
                    <p></p>
                </span>
                <input type="text" id="username" class="fadeIn first" name="username" placeholder="<fmt:message key="registration.Username"/>" required>
                <input type="text" id="fullName" class="fadeIn first" name="fullName" placeholder="<fmt:message key="registration.FullName"/>" required>
                <input type="text" id="email" class="fadeIn first" name="email" placeholder="<fmt:message key="registration.Email"/>" required>
                <input type="password" id="password" class="fadeIn second" name="password" placeholder="<fmt:message key="registration.Password"/>" required>
                <input type="password" id="repeatPassword" class="fadeIn second" name="repeatPassword" placeholder="<fmt:message key="registration.RepeatPassword"/>" required>

                <input type="text" id="city" class="fadeIn second" name="city" placeholder="<fmt:message key="registration.City"/>">
                <input type="text" id="region" class="fadeIn second" name="region" placeholder="<fmt:message key="registration.Region"/>">
                <input type="text" id="school" class="fadeIn third" name="school" placeholder="<fmt:message key="registration.SchoolNumber"/>">
                <input type="text" id="attestatId" class="fadeIn third" name="attestatId" placeholder="<fmt:message key="registration.AttestatID"/>)">
                <input type="text" id="examsId" class="fadeIn third" name="examsId" placeholder="<fmt:message key="registration.ExamsID"/>">

                <input type="submit" class="fadeIn fourth" value="<fmt:message key="Send"/>">
            </form>




        </div>
    </div>
</form>
</body>
</html>
