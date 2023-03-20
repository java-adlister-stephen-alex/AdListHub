<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Selected Ad" />
    </jsp:include>

</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container-fluid">
    <c:choose>
        <c:when test="${edit != null}">
            <h1>Edit Page</h1>
            <div class="d-flex justify-content-center align-items-center">
                <div style="height: 600px; width: 800px" class="card ">
                    <img src="..." class="card-img-top" alt="...">
                    <div class="card-body row">
                        <div class="card-title col">
                            <label for="edit-title">Title: </label>
                            <input id="edit-title" name="edit-title" type="text" value="<c:out value="${ad.get(0).title}"></c:out>">
                            <label for="edit-price">Price: </label>
                            <input id="edit-price" name="edit-price" type="text" value="<c:out value="${ad.get(0).price}"></c:out>">
                        </div>
                        <label for="edit-description">Description: </label>
                        <textarea id="edit-description" name="edit-description">
                        </textarea>
                        <a href="/ads/card?ad_card=${param.ad_card}">
                            <div class="btn btn-outline-secondary">Cancel</div>
                        </a>
                        <a href="/ads/card?ad_card=${param.ad_card}&editfinal=true">
                            <div class="btn btn-outline-secondary">Submit</div>
                        </a>
                    </div>
                </div>
            </div>
        </c:when>
        <c:when test="${delete != null}">
            <h3>Confirm Deletion?</h3>
            <a href="/ads/card?ad_card=${param.ad_card}&deletefinal=true">
                <div class="btn btn-outline-danger">Yes, delete.</div>
            </a>
            <a href="/ads/card?ad_card=${param.ad_card}">
                <div class="btn btn-outline-secondary">No, do not delete.</div>
            </a>
        </c:when>
        <c:otherwise>
            <h1>Ad Page</h1>
            <div class="d-flex justify-content-center align-items-center">
                <div style="height: 600px; width: 800px" class="card ">
                    <img src="..." class="card-img-top" alt="...">
                    <div class="card-body row">
                        <div class="card-title col">
                            <h5 id="ad-card-title">
                                <c:out value="${ad.get(0).title}"></c:out>
                            </h5>
                            <p><c:out value="${ad.get(0).price}"></c:out></p>
                        </div>
                        <p class="card-text col-10"><c:out value="${ad.get(0).description}"></c:out></p>
                        <c:choose>
                            <c:when test="${sessionScope.user.id == ad.get(0).userId}">
                                <a href="/ads/card?ad_card=${param.ad_card}&edit=true">
                                    <div class="btn btn-outline-warning">Edit</div>
                                </a>
                                <a href="/ads/card?ad_card=${param.ad_card}&delete=true">
                                    <div class="btn btn-outline-danger">Delete</div>
                                </a>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>>
</div>
<jsp:include page="/WEB-INF/partials/scripts.jsp"/>
</body>
</html>
