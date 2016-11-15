package com.maketest.service;

import com.maketest.model.Test;

import java.util.List;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface TestService {
    Test findOne(int id);
    List<Test> findAll();
    Test save(Test test);
}
