package com.beaconfire.quizproject.model;

import java.time.LocalDateTime;

public class UserResult {
    private int id;  // Primary key for the result entry
    private int userId;  // Foreign key to the Users table
    private int questionId;  // Foreign key to the Question table
    private int choiceId;  // Foreign key to the Choice table
    private LocalDateTime submitTime;  // Time when the user submitted the answer

    public UserResult(int userId, int questionId, int choiceId, LocalDateTime submitTime) {
        this.userId = userId;
        this.questionId = questionId;
        this.choiceId = choiceId;
        this.submitTime = submitTime;
    }

    public int getId() {
        return id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }
}
