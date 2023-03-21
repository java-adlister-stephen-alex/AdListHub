<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>

</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here Are all the ads!</h1>
    <form action="${pageContext.request.contextPath}/ads/search" method="POST">
        <div class="form-group">
            <label for="search">Search Ad</label>
            <input id="search" name="search" class="form-control" type="text" placeholder="name" value="${sessionScope.search}" required>
            <input type="submit" class="btn btn-primary btn-block my-3" name="submit-search" value="search">
        </div>
    </form>

    <div class="row row-cols-1 row-cols-md-2 g-4">
    <c:forEach var="ad" items="${ads}">
        <a id="ad-card" href="/ads/card?ad_card=${ad.id}">
            <div style="height: 100px;" class="col">
                <div class="card">
                    <img src="..." class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${ad.title}"></c:out></h5>
                        <p class="card-text"><c:out value="${ad.description}"></c:out></p>
                    </div>
                </div>
            </div>
        </a>
    </c:forEach>
    </div>
</div>
<jsp:include page="/WEB-INF/partials/scripts.jsp"/>
</body>
</html>