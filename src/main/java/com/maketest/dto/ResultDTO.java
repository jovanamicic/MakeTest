package com.maketest.dto;

import com.maketest.model.Result;
import com.maketest.model.Test;
import com.maketest.model.UserAnswer;

import java.util.Set;

/**
 * Created by Jovana Micic on 15-Nov-16.
 */
public class ResultDTO {
    private int id;
    private int percentage;
    private Set<UserAnswer> userAnswerResult;
    private Test test;

    public ResultDTO(){}

    public ResultDTO(Result r){
        this(r.getId(),r.getPercentage(),r.getUserAnswerResult(),r.getTestResult());
    }

    public ResultDTO(int id, int percentage, Set<UserAnswer> userAnswerResult, Test test) {
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

    public Set<UserAnswer> getUserAnswerResult() {
        return userAnswerResult;
    }

    public void setUserAnswerResult(Set<UserAnswer> userAnswerResult) {
        this.userAnswerResult = userAnswerResult;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
