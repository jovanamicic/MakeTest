package com.maketest.service;

import com.maketest.model.UserAnswer;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface UserAnswerService {

    UserAnswer findOne(int id);
    UserAnswer save(UserAnswer userAnswer);
}
