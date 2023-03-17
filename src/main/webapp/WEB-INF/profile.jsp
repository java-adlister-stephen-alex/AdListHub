<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome to your page, <c:out value="${sessionScope.user.username}"></c:out>!</h1>
    </div>
    <div>
        <div class="mx-3"><a href="/ads/create">Create An Ad</a></div>
    </div>

</body>
</html>
