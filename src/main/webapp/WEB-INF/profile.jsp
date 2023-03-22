<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile"/>
    </jsp:include>
</head>
<body class="all-pages-bg">
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h1 class="text-center my-4">Welcome, <c:out value="${sessionScope.user.username}"></c:out></h1>
</div>
<div class="d-flex justify-content-center">
    <hr class="w-75">
</div>
<h3 class="text-center">Your AdListHub Ads</h3>
<div class="container-fluid d-flex justify-content-evenly flex-wrap">
        <c:forEach var="ad" items="${ads}">
            <a id="ad-card" href="/ads/card?ad_card=${ad.id}">
                <div class="card my-4">
                    <div class="card-body profile-cards">
                        <h5 class="card-title">Title: <c:out value="${ad.title}"></c:out></h5>
                        <hr>
                        <p class="card-text">Description: <c:out value="${ad.description}"></c:out></p>
                    </div>
                </div>
            </a>
        </c:forEach>
</div>
</body>
</html>
