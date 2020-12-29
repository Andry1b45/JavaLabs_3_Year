<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>

<html lang="${sessionScope.language}">
<head>
    <title>Block student</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style><%@include file="/css/forms.css" %></style>
</head>

<body>
<form method="post" action="${pageContext.request.contextPath}/block" class="applicationForm">
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <div class="fadeIn first">
                <img src="https://www.qbrobotics.com/wp-content/uploads/2018/03/sample-logo-470x235.png" id="icon" alt="User Icon" />
            </div>
            <span class="error_message" id="error">${requestScope.get('error')}</span>
            <p></p>
            <form class="page-form" action="${pageContext.request.contextPath}/blockstudent" method="get">
                <span class = "page-form">
                    <span>
                      <label for="Statement"><fmt:message key="blockStudent.Statement"/></label>
                         <select class="btn btn-info dropdown-toggle" name="Statement" id="Statement">
                            <c:choose>
                                <c:when test="${param.Statement =='Block'}">
                                    <option value="true" selected><fmt:message key="blockStudent.Block"/></option>
                                    <option value="false"><fmt:message key="blockStudent.Unblock"/></option>
                                    <option value="none"><fmt:message key="viewFaculties.None"/></option>
                                </c:when>
                                <c:when test="${param.Statement=='Unblock'}">
                                    <option value="true"><fmt:message key="blockStudent.Block"/></option>
                                    <option value="false" selected><fmt:message key="blockStudent.Unblock"/></option>
                                    <option value="none"><fmt:message key="viewFaculties.None"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="none" selected><fmt:message key="viewFaculties.None"/></option>
                                    <option value="true"><fmt:message key="blockStudent.Block"/></option>
                                    <option value="false"><fmt:message key="blockStudent.Unblock"/></option>
                                </c:otherwise>
                            </c:choose>
                        </select></span>
                    <p></p>
                </span>
                <input type="text" id="email" class="fadeIn first" name="email" placeholder="<fmt:message key="viewStudents.Email"/>" required>
                <input type="submit" class="fadeIn second" value="<fmt:message key="Send"/>">
            </form>

            <div class="form-group">
                <button type="submit" class="fadeIn fourth" value="Students" style="background-color: #39ace7;">
                    <a method="post" href="${pageContext.request.contextPath}/viewStudents"><fmt:message key="adminMenu.ViewStudents"/></a>
                </button>
                <button type="submit" class="fadeIn fourth" value="Menu" style="background-color: #39ace7;">
                    <a method="post" href="${pageContext.request.contextPath}/jsp/adminMenu.jsp"><fmt:message key="Menu"/></a>
                </button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
