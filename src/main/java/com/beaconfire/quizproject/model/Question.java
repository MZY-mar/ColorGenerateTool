package com.beaconfire.quizproject.model;

public class Question {
    private int Id;
    private String question_name;

    public Question() {
    }
    public Question(String question_name) {
        this.question_name = question_name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }
}
