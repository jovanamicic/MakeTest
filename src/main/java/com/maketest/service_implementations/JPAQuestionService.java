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
public class JPAQuestionService implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question findOne(int id) {
        Question question = questionRepository.findOne(id);
        Question retVal = null;

        if(question!=null)
            retVal = question;
        else
            throw new IllegalArgumentException("There is no data with id: " + id);
        return retVal;
    }
}
