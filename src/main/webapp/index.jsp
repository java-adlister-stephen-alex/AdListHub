<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Welcome to my site!" />
    </jsp:include>
</head>
<body class="index-bg">
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container-fluid">
        <h1 class="adlister-front-page-header mx-4 my-0">Welcome to the AdlistHub</h1>
        <div class="adlister-front-page-subheader mx-4 my-0">Your search ends here - find everything you need with us</div>
    </div>
</body>
</html>
