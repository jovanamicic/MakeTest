package com.maketest.service;

import com.maketest.model.Question;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface QuestionService {

    Question findOne(int id);
    Question save(Question q);
}
