<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
    <div class="container-fluid bg-light">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand fs-1" href="${pageContext.request.contextPath}/ads">Adlister</a>
        </div>
        <div class="nav">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <div class="d-flex justify-content-evenly">
                        <div class="mx-4"><a href="${pageContext.request.contextPath}/login">Login</a></div>
                        <div class="mx-4"><a href="${pageContext.request.contextPath}/register">Register For Our Site</a></div>
                        <div class="mx-4"><a href="${pageContext.request.contextPath}/ads">View Ads</a></div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="d-flex justify-content-evenly">
                        <div class="mx-4"><a href="${pageContext.request.contextPath}/profile">Profile</a></div>
                        <div class="mx-4"><a href="${pageContext.request.contextPath}/ads">View Ads</a></div>
                        <div class="mx-4"><a href="${pageContext.request.contextPath}/ads/create">Create An Ad</a></div>
                        <div class="mx-4"><a href="${pageContext.request.contextPath}/logout">Logout</a></div>
                    </div>
                </c:otherwise>

            </c:choose>
        </div>
    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
