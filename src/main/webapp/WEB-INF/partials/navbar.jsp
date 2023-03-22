<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default m-0 p-3">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand left-nav mx-4" href="${pageContext.request.contextPath}/ads">AdlistHub</a>
        </div>
        <div class="nav">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <div class="d-flex justify-content-evenly">
                        <div class="mx-3"><a class="right-nav" href="/login">Login</a></div>
                        <div class="mx-3"><a class="right-nav" href="/register">Register For Our Site</a></div>
                        <div class="mx-3"><a class="right-nav" href="/ads">View Ads</a></div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="d-flex justify-content-evenly">
                        <div class="mx-3"><a class="right-nav" href="/profile">Profile</a></div>
                        <div class="mx-3"><a class="right-nav" href="/ads">View Ads</a></div>
                        <div class="mx-3"><a class="right-nav" href="/ads/create">Create An Ad</a></div>
                        <div class="mx-3"><a class="right-nav" href="/logout">Logout</a></div>
                    </div>
                </c:otherwise>

            </c:choose>
    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
