package com.maketest.service_implementations;

import com.maketest.dto.TestDTO;
import com.maketest.model.Test;
import com.maketest.repository.TestRepository;
import com.maketest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Service
public class TestServiceImplementation implements TestService {

    @Autowired
    TestRepository testRepository;

    @Override
    public Test findOne(int id) {
        return testRepository.findOne(id);
    }

    @Override
    public Test save(Test test) {
        return testRepository.save(test);
    }


}
