<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>

<html lang="${sessionScope.language}">
<head>
    <title>View Students</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style><%@include file="/css/forms.css" %></style>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/viewStudents" class="applicationForm">
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
                                <th><fmt:message key="viewStudents.ID"/></th>
                                <th><fmt:message key="viewStudents.Username"/></th>
                                <th><fmt:message key="viewStudents.Name"/></th>
                                <th><fmt:message key="viewStudents.Email"/></th>
                                <th><fmt:message key="viewStudents.Blocked"/></th>
                                <th><fmt:message key="viewStudents.Role"/></th>
                            </tr>
                            <c:set var="students" value="${requestScope.get('students')}"/>
                            <c:forEach var="user" items="${requestScope.get('students')}" >
                                <tr>
                                    <td>${user.getId()}</td>
                                    <td>${user.getUsername()}</td>
                                    <td>${user.getFull_name()}</td>
                                    <td>${user.getEmail()}</td>
                                    <td>${user.getConvertedBlocked()}</td>
                                    <td>${user.getConvertedRole()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <c:set var="href" value="viewStudents?" scope="request"/>
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
