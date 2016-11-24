package com.maketest.dto;

import com.maketest.model.Result;
import com.maketest.model.Test;
import com.maketest.model.UserAnswer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jovana Micic on 15-Nov-16.
 */
public class ResultDTO {
    private int id;
    private int percentage;
    private Set<UserAnswerDTO> userAnswerResult;
    private TestDTO test;

    public ResultDTO(){}

    public ResultDTO(Result r){
        this.id = r.getId();
        this.percentage = r.getPercentage();
        this.test = new TestDTO(r.getTestResult());
        Set<UserAnswerDTO> answers = new HashSet<>();
        for (UserAnswer answer: r.getUserAnswerResult()) {
            answers.add(new UserAnswerDTO(answer));
        }

        this.userAnswerResult = answers;
    }

    public ResultDTO(int id, int percentage, Set<UserAnswerDTO> userAnswerResult, TestDTO test) {
        this.id = id;
        this.percentage = percentage;
        this.userAnswerResult = userAnswerResult;
        this.test = test;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Set<UserAnswerDTO> getUserAnswerResult() {
        return userAnswerResult;
    }

    public void setUserAnswerResult(Set<UserAnswerDTO> userAnswerResult) {
        this.userAnswerResult = userAnswerResult;
    }

    public TestDTO getTest() {
        return test;
    }

    public void setTest(TestDTO test) {
        this.test = test;
    }
}
