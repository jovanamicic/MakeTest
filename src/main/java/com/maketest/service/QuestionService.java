<<<<<<< HEAD
package com.maketest.service;

import com.maketest.model.Question;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface QuestionService {

    Question findOne(int id);
    Question save(Question q);
}
=======
package com.maketest.service;

import com.maketest.model.Question;

import java.util.List;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface QuestionService {

    Question findOne(int id);
    Question save(Question q);
    List<Question> findByTest(int id);
}
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
