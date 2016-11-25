<<<<<<< HEAD
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
=======
package com.maketest.service_implementations;

import com.maketest.model.Test;
import com.maketest.repository.TestRepository;
import com.maketest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<Test> findAll() {
        List<Test> retVal = testRepository.findAll();
        return retVal;
    }

    @Override
    public Test save(Test test) {
        return testRepository.save(test);
    }

    @Override
    public List<Test> findByCategory(String category) {
        return testRepository.findByCategory(category);
    }

    @Override
    public List<String> findAllByCategory() {
        List<Test> tests = testRepository.findAll();
        List<String> categories = new ArrayList<>();
        for (Test t: tests) {
            categories.add(t.getCategory());
        }
        return categories;
    }


}
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
