package com.maketest.dto;

import com.maketest.model.Answer;
import com.maketest.model.UserAnswer;

/**
 * Created by Jovana Micic on 15-Nov-16.
 */
public class UserAnswerDTO {
    private int id;
    private AnswerDTO userAnswer;

    public UserAnswerDTO(){}

    public UserAnswerDTO(UserAnswer ua){
        this(ua.getId(),new AnswerDTO(ua.getUserAnswer()));
    }

    public UserAnswerDTO(int id, AnswerDTO userAnswer) {
        this.id = id;
        this.userAnswer = userAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnswerDTO getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(AnswerDTO userAnswer) {
        this.userAnswer = userAnswer;
    }
}
