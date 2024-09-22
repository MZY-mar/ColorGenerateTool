<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Quiz Page</title>
    <style>
        <%@ include file="../css/style.css"%>
    </style></head>
<body>
<div class="container">
    <h2>Question ${question.id}.</h2>
    <p>${question.question_name}</p>

    <form action="${pageContext.request.contextPath}/save-answer" method="post">
        <input type="hidden" name="questionId" value="${question.id}">
        <div class="options">
            <ul>
                <c:forEach var="choice" items="${choices}">
                    <li>
                        <input type="radio" name="choiceId" value="${choice.id}" required>
                        <label>${choice.choice_name}</label>
                    </li>

                    <c:if test="${currentQuestionIndex == 0}">
                        <div class="season-container">
                            <% for (int i = 1; i < 4; i++) { %>
                            <div class="${choice.choice_name}<%= i %>"></div>
                            <% } %>
                        </div>
                    </c:if>

                    <c:if test="${currentQuestionIndex == 1}">
                        <div class="season-container">
                            <% for (int i = 1; i < 3; i++) { %>
                            <div class="${choice.choice_name}<%= i %>"></div>
                            <% } %>
                        </div>
                    </c:if>
                </c:forEach>
            </ul>
        </div>

        <div>
            <c:if test="${currentQuestionIndex < allQuestions.size() - 1}">
                <button type="submit">Next</button>
            </c:if>
            <c:if test="${currentQuestionIndex == allQuestions.size() - 1}">
                <button type="submit">Submit</button>
            </c:if>
        </div>
    </form>
</div>

</body>
</html>
