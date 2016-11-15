package com.maketest.dto;

import com.maketest.model.Answer;

/**
 * Created by Jovana Micic on 15-Nov-16.
 */
public class AnswerDTO {
    private int id;
    private String answerText;

    public AnswerDTO(){}

    public AnswerDTO(Answer a){
        this(a.getId(),a.getAnswerText());
    }

    public AnswerDTO(int id, String answerText) {
        this.id = id;
        this.answerText = answerText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
