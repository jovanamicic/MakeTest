package com.maketest.service_implementations;

import com.maketest.model.Answer;
import com.maketest.repository.AnswerRepository;
import com.maketest.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Service
public class JPAAnswerService implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public Answer findOne(int id) {
        Answer answer = answerRepository.findOne(id);
        Answer retVal = null;
        if (answer!= null)
            retVal = answer;
        else
            throw new IllegalArgumentException("There is no data with id: " + id);
        return  retVal;
    }
}
