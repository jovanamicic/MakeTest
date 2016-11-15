package com.maketest.service_implementations;

import com.maketest.model.UserAnswer;
import com.maketest.repository.UserAnswerRepository;
import com.maketest.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Service
public class UserAnswerServiceImplementation implements UserAnswerService {

    @Autowired
    UserAnswerRepository userAnswerRepository;

    @Override
    public UserAnswer findOne(int id) {
        return userAnswerRepository.findOne(id);
    }

    @Override
    public UserAnswer save(UserAnswer userAnswer) {
        return userAnswerRepository.save(userAnswer);
    }
}
