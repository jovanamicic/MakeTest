<<<<<<< HEAD
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
=======
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
    List<String> findAllByCategory();
    List<Test> findByCategory(String category);
}
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
