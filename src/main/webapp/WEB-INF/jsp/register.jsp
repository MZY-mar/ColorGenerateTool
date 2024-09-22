<!DOCTYPE html>
<html>
<head>
    <title>Registration Form</title>
    <style>
        <%@ include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="LoginRegistercontainer">
<h1>Registration</h1>
<c:if test="${not empty message}">
    <p style="color: green;">${message}</p>
    </c:if>
<form action="register" method="post" class="simple-form">
    <div class="form-group">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    </div>

    <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    </div>

    <div class="form-group">
    <label for="firstname">First Name:</label>
    <input type="text" id="firstname" name="firstname" required>
    </div>
    <div class="form-group">
    <label for="lastname">Last Name:</label><br>
    <input type="text" id="lastname" name="lastname" required>
    </div>
    <button type="submit" value="Register" class="loginbtn">Register
    </button>
</form>
</body>
</div>
</html>
