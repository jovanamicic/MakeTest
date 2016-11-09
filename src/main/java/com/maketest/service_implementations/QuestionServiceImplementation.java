package com.maketest.service_implementations;

import com.maketest.model.Question;
import com.maketest.repository.QuestionRepository;
import com.maketest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */

@Service
public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question findOne(int id) {
        return questionRepository.findOne(id);
    }

    @Override
    public Question save(Question q) {
        return questionRepository.save(q);
    }
}
