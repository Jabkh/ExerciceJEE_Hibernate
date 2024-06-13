<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/bootstrap-includes.html" %>
    <title>Logout</title>
</head>
<body>
<main class="container">
    <div class="row my-3">
        <div class="col-6 offset-3 rounded text-bg-grey p-3">
            <h1 class="fw-light">Logout</h1>
            <hr>
            <p>You have been logged out.</p>
            <p><a href="${pageContext.request.contextPath}/login.jsp">Back to Login</a></p>
        </div>
    </div>
</main>
</body>
</html>
