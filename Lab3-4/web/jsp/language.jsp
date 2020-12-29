<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>

<html lang="${param.lang}">
<head>
    <title>Language</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style><%@include file="/css/forms.css" %></style>
</head>

<body>
<form method="post" action="${pageContext.request.contextPath}/language">
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <div class="fadeIn first">
                <img src="https://www.qbrobotics.com/wp-content/uploads/2018/03/sample-logo-470x235.png" id="icon" alt="User Icon" />
            </div>
            <span class="error_message" id="error">${requestScope.get('error')}</span>
            <p></p>
                <select class="btn btn-info dropdown-toggle" name="language" id="language">

                    <c:choose>
                        <c:when test="${param.language =='ukr'}">
                            <option value ="en"><fmt:message key="index.Language.en"/></option>
                            <option value ="ukr" selected><fmt:message key="index.Language.ukr"/></option>
                            <option value="rus"><fmt:message key="index.Language.ru"/></option>
                        </c:when>
                        <c:when test="${param.language =='rus'}">
                            <option value ="en"><fmt:message key="index.Language.en"/></option>
                            <option value ="ukr"><fmt:message key="index.Language.ukr"/></option>
                            <option value="rus" selected><fmt:message key="index.Language.ru"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value ="en" selected><fmt:message key="index.Language.en"/></option>
                            <option value ="ukr"><fmt:message key="index.Language.ukr"/></option>
                            <option value="rus"><fmt:message key="index.Language.ru"/></option>
                        </c:otherwise>
                    </c:choose>
                </select>

                <button type="submit" class="fadeIn fourth" value="language" style="background-color: #39ace7;">
                    <fmt:message key="index.Language"/>
                </button>
        </div>
    </div>
</form>
</body>
</html>
