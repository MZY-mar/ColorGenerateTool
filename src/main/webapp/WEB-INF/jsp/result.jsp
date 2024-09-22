<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User answer</title>
    <style>
        <%@ include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
<h1>Result </h1>

<h2>User id :  ${userId} quiz answers</h2>
<c:choose>
    <c:when test="${not empty formattedAnswers}">
        <table border="1" class="resulttable">
            <thead>
            <tr>
                <th>Question id :</th>
                <th>Answer: </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${formattedAnswers}">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Could not find user answer</p>
    </c:otherwise>
</c:choose>
    <h2>User's Color result</h2>
<c:choose>
    <c:when test="${not empty colorResult}">
        <table border="1" class="resulttable">
            <thead>
            <tr>
                <th>Color Set:</th>
                <th>Color :</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="colorList" items="${colorResult}">
                <tr>
                    <td>Color Set</td>
                    <td>
                        <!-- Inner loop to iterate through each color in the sublist -->
                        <c:forEach var="color" items="${colorList}">
                            <span style="background-color:${color}; padding: 5px;">${color}</span>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Could not find user answer</p>
    </c:otherwise>
</c:choose>
    <form action="/saveQuizResultInDB" method="post">
    <button type="submit">Save</button>
    </form>

    <form action="/restQuiz" method="post">
        <button type="submit"> Reset</button>
    </form>

</div>
</body>
</html>
