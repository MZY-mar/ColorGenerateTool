<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Question</title>

</head>
<body>
<h1>Question Details</h1>
<p>Question ID: ${question.id}</p>
<p>Question: ${question.question_name}</p>
<form action="/submit-answer" method="post">
    <input type="hidden" name="questionId" value="${question.id}">

<h2>Choices</h2>
<ul>
    <c:forEach var="choice" items="${choices}">
        <div>
            <input type="radio" name="choiceId" value="${choice.id}" required>
            <label>${choice.choice_name}</label>
        </div>
    </c:forEach>
</ul>
<!-- nav -->
<div>
    <c:if test="${currentQuestionIndex > 0}">
    <a href="/question?id=${currentQuestionIndex}">Previous</a>
    </c:if>

    <c:if test="${currentQuestionIndex < allQuestions.size() - 1}">
    <a href="/question?id=${currentQuestionIndex + 2}">Next</a>
    </c:if>
     <c:if test="${currentQuestionIndex == allQuestions.size() - 1}">
            <button type="submit">Submit</button>
        </c:if>
</div>
</form>
</body>
</html>