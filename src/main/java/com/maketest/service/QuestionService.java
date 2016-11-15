package com.maketest.service;

import com.maketest.model.Question;

import java.util.List;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface QuestionService {

    Question findOne(int id);
    Question save(Question q);
    List<Question> findByTest(int id);
}
