<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Register For Our Website"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <h1>Register for our website</h1>
    <form action="/register" method="POST">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" class="form-control" type="text" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" name="email" class="form-control" type="text" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <div class="d-flex">
                <input id="password" name="password" class="form-control" required>
                <i class="bi bi-eye-slash fs-1 mx-2"></i>
            </div>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm Password</label>
            <div class="d-flex">
                <input id="confirmPassword" name="confirmPassword" class="form-control" required>
                <i class="bi bi-eye-slash fs-1 mx-2"></i>
            </div>
        </div>
        <input type="submit" class="btn btn-block btn-primary my-4">
    </form>
</div>

<%@include file="partials/toggle-pw.jsp"%>
<%@include file="partials/scripts.jsp"%>
</body>
</html>
