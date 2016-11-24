<<<<<<< HEAD
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
=======
package com.maketest.service_implementations;

import com.maketest.model.Question;
import com.maketest.model.Test;
import com.maketest.repository.QuestionRepository;
import com.maketest.repository.TestRepository;
import com.maketest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */

@Service
public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TestRepository testRepository;

    @Override
    public Question findOne(int id) {
        return questionRepository.findOne(id);
    }

    @Override
    public Question save(Question q) {
        return questionRepository.save(q);
    }

    @Override
    public List<Question> findByTest(int id) {
        Test test = testRepository.findOne(id);
        return questionRepository.findByTestQuestions(test);
    }
}
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
