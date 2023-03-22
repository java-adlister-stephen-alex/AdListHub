<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad"/>
    </jsp:include>
</head>
<body class="all-pages-bg">
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container d-flex login-container justify-content-center align-items-center">
    <div class="container-fluid login-container-sizing">
        <h1 class="text-center my-3">Create a new Ad</h1>
        <form action="/ads/create" method="post">
            <div class="form-group my-2">
                <label for="title">Title</label>
                <c:choose>
                    <c:when test="${createTitle != null}">
                        <input id="title" name="title" class="form-control" type="text" required value="${createTitle}">
                    </c:when>
                    <c:otherwise>
                        <input id="title" name="title" class="form-control" type="text" required>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group my-2">
                <label for="description">Description</label>
                <c:choose>
                    <c:when test="${createDescription != null}">
                        <input id="description" name="description" class="form-control" type="text"
                               value="${createDescription}" required>
                    </c:when>
                    <c:otherwise>
                        <input id="description" name="description" class="form-control" type="text" required>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group my-2">
                <label for="price">Price</label>
                <input id="price" name="price" class="form-control" type="text" required>
                <c:choose>
                    <c:when test="${sessionScope.priceValidation != null}">
                        <div>Invalid price</div>
                    </c:when>
                </c:choose>
            </div>
            <div class="my-2">
                <div class="text-center">Select Appropriate Categories</div>
                <div class="categories">
                    <c:forEach items="${sessionScope.categories}" var="category">
                        <div class="d-flex justify-content-start">
                            <input class="form-check-input" type="checkbox"
                                   id="${category.toString().replaceAll(" ", "")}"
                                   name="${category.toString().replaceAll(" ", "")}"
                                   value="${category.toString().replaceAll(" ", "")}">
                            <label class="form-check-label"
                                   for="${category.toString().replaceAll(" ", "")}">${category}</label>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <input type="submit" class="btn btn-block btn-primary submit-btn" value="Create Ad">
        </form>
    </div>
</div>
</body>
</html>
