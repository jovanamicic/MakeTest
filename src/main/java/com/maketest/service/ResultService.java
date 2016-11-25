<<<<<<< HEAD
package com.maketest.service;

import com.maketest.model.Result;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface ResultService {

    Result findOne(int id);
}
=======
package com.maketest.service;

import com.maketest.dto.UserAnswerDTO;
import com.maketest.model.Result;
import com.maketest.model.UserAnswer;

import java.util.Set;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface ResultService {

    Result findOne(int id);
    Result save(Result result);
    int calculate(Set<UserAnswerDTO> userAnswers);
}
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
