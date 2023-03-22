<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads"/>
    </jsp:include>

</head>
<body class="all-pages-bg">
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h1 class="text-center my-3">Search All Ads</h1>
    <form action="${pageContext.request.contextPath}/ads/search" method="POST">
        <div class="form-group">
            <label for="search">Search Ad</label>
            <input id="search" name="search" class="form-control" type="text" placeholder="name"
                   value="${sessionScope.search}" required>
            <input type="submit" class="btn btn-primary btn-block my-2 submit-btn" name="submit-search" value="Search">
        </div>
    </form>
</div>
<hr>

<div class="container-fluid w-75 d-flex justify-content-evenly flex-wrap">
    <c:forEach var="ad" items="${ads}">
        <a id="ad-card" href="/ads/card?ad_card=${ad.id}">
            <div>
                <div class="card profile-cards my-3">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${ad.title}"></c:out></h5>
                        <p class="card-text"><c:out value="${ad.description}"></c:out></p>
                    </div>
                </div>
            </div>
        </a>
    </c:forEach>
</div>

<jsp:include page="/WEB-INF/partials/scripts.jsp"/>
</body>
</html>