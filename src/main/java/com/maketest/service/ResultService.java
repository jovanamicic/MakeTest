package com.maketest.service;

import com.maketest.model.Result;
import com.maketest.model.UserAnswer;

import java.util.Set;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface ResultService {

    Result findOne(int id);
    Result save(Result result);
    int calculate(Set<UserAnswer> userAnswers);
}
