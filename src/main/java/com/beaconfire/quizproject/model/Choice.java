package com.beaconfire.quizproject.model;

public class Choice {
    public  int Id;
    public  String choice_name;
    public  int question_id;

    public  Choice(){}
    public Choice(String choice_name, int question_id) {
        this.choice_name = choice_name;
        this.question_id = question_id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getChoice_name() {
        return choice_name;
    }

    public void setChoice_name(String choice_name) {
        this.choice_name = choice_name;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
}
