package com.maketest.dto;

import com.maketest.model.Answer;
import com.maketest.model.UserAnswer;

/**
 * Created by Jovana Micic on 15-Nov-16.
 */
public class UserAnswerDTO {
    private int id;
    private Answer userAnswer;

    public UserAnswerDTO(){}

    public UserAnswerDTO(UserAnswer ua){
        this(ua.getId(),ua.getUserAnswer());
    }

    public UserAnswerDTO(int id, Answer userAnswer) {
        this.id = id;
        this.userAnswer = userAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Answer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Answer userAnswer) {
        this.userAnswer = userAnswer;
    }
}
