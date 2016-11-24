package com.maketest.service;

import com.maketest.dto.TestDTO;
import com.maketest.model.Test;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface TestService {
    Test findOne(int id);
    Test save(Test test);
}
