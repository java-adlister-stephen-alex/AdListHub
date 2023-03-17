<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <h1>Please Log In</h1>
    <form action="/login" method="POST">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" class="form-control" type="text" value="ShawnHardin">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <div class="d-flex">
                <input id="password" name="password" class="form-control" value="Shawn2023">
                <i class="bi bi-eye-slash fs-1 mx-2"
                   id="togglePassword"></i>
            </div>
        </div>
        <input type="submit" class="btn btn-primary btn-block my-3" value="Log In">
    </form>
</div>

<%@include file="partials/toggle-pw.jsp"%>
<%@include file="partials/scripts.jsp"%>
</body>
</html>
