<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        <%@ include file="../css/style.css"%>
    </style>
</head>
<body>

<div class="LoginRegistercontainer">
    <h1>Login</h1>
    <br>
    <!-- Display error message if present -->
    <c:if test="${not empty error}">
        <p style="color: red; font-size: large">${error}</p>
    </c:if>
<form action="login" method="post" class="simple-form">
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>
    <br>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <br>
    <button type="submit" class="loginbtn">Login</button>
</form>
</div>

</body>
</html>
