<%@ page import="org.example.exerciceProduct.service.UserService" %>
<%@ page import="org.example.exerciceProduct.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/bootstrap-includes.html" %>
    <title>Login</title>
</head>
<body>
<main class="container">
    <div class="row my-3">
        <div class="col-6 offset-3 rounded text-bg-grey p-3">
            <h1 class="fw-light">Login</h1>
            <hr>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </div>
    </div>
</main>
</body>
</html>
