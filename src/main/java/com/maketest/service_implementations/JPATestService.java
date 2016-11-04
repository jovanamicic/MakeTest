package com.maketest.service_implementations;

import com.maketest.model.Test;
import com.maketest.repository.TestRepository;
import com.maketest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Service
public class JPATestService implements TestService {

    @Autowired
    TestRepository testRepository;

    @Override
    public Test findOne(int id) {
        Test test = testRepository.findOne(id);
        Test retVal = null;

        if(test!=null)
            retVal = test;
        else
            throw new IllegalArgumentException("There is no data with id: " + id);
        return retVal;
    }
}
