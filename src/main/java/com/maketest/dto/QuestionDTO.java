package com.maketest.dto;

import com.maketest.model.Answer;
import com.maketest.model.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jovana Micic on 09-Nov-16.
 */
public class QuestionDTO {
    private Integer id;
    private String questionText;
    private String correctAnswer;
    private Set<AnswerDTO> answers;
    private int testId;

    public QuestionDTO(Question q){
        Set<AnswerDTO> qanswers = new HashSet<>();
        for (Answer a : q.getAnswers()) {
            qanswers.add(new AnswerDTO(a));
        }
        this.id = q.getId();
        this.questionText = q.getQuestionText();
        this.correctAnswer = q.getCorrectAnswer();
        this.answers = qanswers;
        this.testId = q.getTestQuestions().getId();
    }

    public QuestionDTO(){}

    public QuestionDTO(Integer id, String questionText, String correctAnswer, Set<AnswerDTO> answers, int testId) {
        this.id = id;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.testId = testId;
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

    public Set<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerDTO> answers) {
        this.answers = answers;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
}
