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
            <input id="search" name="search" class="form-control" type="text" placeholder="name" required>
            <input type="submit" class="btn btn-primary btn-block my-3" name="submit-search" value="search">
        </div>
    </form>

    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6 my-5">
            <h2><c:out value="${ad.title}"></c:out></h2>
            <div><c:out value="${ad.description}"></c:out></div>
            <div>Price: $<c:out value="${ad.price}"></c:out>USD</div>
        </div>
    </c:forEach>
</div>
<jsp:include page="/WEB-INF/partials/scripts.jsp"/>
</body>
</html>