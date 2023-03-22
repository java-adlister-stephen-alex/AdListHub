<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form action="/login" method="POST" novalidate>
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" class="form-control" type="text" value="${sessionScope.stickyUsernameRegister}" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <div class="d-flex">
                <input id="password" name="password" class="form-control" required>
                <i class="bi bi-eye-slash fs-1 mx-2"
                   id="togglePassword"></i>
            </div>
        </div>
        <input type="submit" class="btn btn-primary btn-block my-3" value="Log In">
        <c:choose>
            <c:when test="${sessionScope.userDNE != null || sessionScope.passwordIncorrect != null}">
                <div>Invalid login credentials</div>
            </c:when>
        </c:choose>
    </form>
</div>

<%@include file="partials/toggle-pw.jsp"%>
<%@include file="partials/scripts.jsp"%>
</body>
</html>
