package com.maketest.dto;

import com.maketest.model.Answer;
import com.maketest.model.Question;

import java.util.List;
import java.util.Set;

/**
 * Created by Jovana Micic on 09-Nov-16.
 */
public class QuestionDTO {
    private Integer id;
    private String questionText;
    private String correctAnswer;
    private Set<Answer> answers;

    public QuestionDTO(Question q){
        this(q.getId(), q.getQuestionText(), q.getCorrectAnswer(), q.getAnswers());
    }

    public QuestionDTO(){}

    public QuestionDTO(Integer id, String questionText, String correctAnswer, Set<Answer> answers) {
        this.id = id;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
