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
                <form action="/ads/card?edit=true" method="post">
                    <div style="height: 600px; width: 800px" class="card ">
                        <img src="..." class="card-img-top" alt="...">
                        <div class="card-body row">
                            <div class="card-title col">
                                <label for="edit-title">Title: </label>
                                <input id="edit-title" name="edit-title" type="text" value="${ad.get(0).title}" disabled>
                                <label for="edit-price">Price: </label>
                                <input id="edit-price" name="edit-price" type="text" value="${ad.get(0).price}" disabled>
                            </div>
                            <label for="edit-description">Description: </label>
                            <textarea id="edit-description" name="edit-description"  disabled>
                                ${ad.get(0).description}
                            </textarea>
                            <c:choose>
                                <c:when test="${adCategories.size() != 0}">
                                    <div>
                                        Categories:
                                        <c:forEach var="category" items="${adCategories}">
                                            <span>${category.category} </span>
                                        </c:forEach>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${sessionScope.user.id == ad.get(0).userId}">
                            <a id="edit-btn">
                                <div class="btn btn-outline-warning">Edit</div>
                            </a>
                            <a id="cancel-edit-btn" class="d-none">
                                <div class="btn btn-outline-secondary">Cancel</div>
                            </a>
                            <button id="confirm-edit-btn" class="d-none btn btn-outline-success" type="submit">
                                Save Changes
                            </button>
                            <a id="delete-btn" class="btn btn-outline-danger" href="/ads/card?ad_card=${param.ad_card}&delete=true">
                                Delete
                            </a>
                        </c:when>
                    </c:choose>
                    </form>
                </div>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="/WEB-INF/partials/scripts.jsp"/>
<script>
    <jsp:include page="/WEB-INF/partials/script.js"/>
</script>
</body>
</html>
