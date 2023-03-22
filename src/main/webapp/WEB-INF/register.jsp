<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Register For Our Website"/>
    </jsp:include>
</head>
<body class="all-pages-bg">
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container d-flex login-container justify-content-center align-items-center">
    <div class="container-fluid login-container-sizing">
        <h1 class="text-center">Register for our website</h1>
        <form action="/register" method="POST">
            <div class="form-group my-2">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text"
                       value="${sessionScope.stickyUsernameRegister}" required>
            </div>
            <div class="form-group my-2">
                <label for="email">Email</label>
                <input id="email" name="email" class="form-control" type="text"
                       value="${sessionScope.stickyEmailRegister}" required>
            </div>
            <div class="form-group my-2">
                <label for="password">Password</label>
                <div class="d-flex">
                    <input id="password" name="password" class="form-control" required>
                    <i class="bi bi-eye-slash fs-1 mx-2"></i>
                </div>
            </div>
            <div class="form-group my-2">
                <label for="confirmPassword">Confirm Password</label>
                <div class="d-flex">
                    <input id="confirmPassword" name="confirmPassword" class="form-control" required>
                    <i class="bi bi-eye-slash fs-1 mx-2"></i>
                </div>
            </div>
            <input type="submit" class="btn btn-block btn-primary my-2 submit-btn" value="Register">
            <c:choose>
                <c:when test="${sessionScope.usernameEmailExists != null}">
                    <div>Username/Email already exists!</div>
                </c:when>
            </c:choose>
        </form>
    </div>
</div>
    <%@include file="partials/toggle-pw.jsp" %>
    <%@include file="partials/scripts.jsp" %>
</body>
</html>
