package com.maketest.service_implementations;

import com.maketest.model.Answer;
import com.maketest.model.Question;
import com.maketest.model.Result;
import com.maketest.model.UserAnswer;
import com.maketest.repository.ResultRepository;
import com.maketest.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Service
public class ResultServiceImplementation implements ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Override
    public Result findOne(int id) {
        return resultRepository.findOne(id);
    }

    @Override
    public Result save(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public int calculate(Set<UserAnswer> userAnswers) {
        int percentage = 0;
        int correctAnswers = 0;
        int totalQuestions = userAnswers.size();

        for (UserAnswer ua : userAnswers) {
            Answer ans = ua.getUserAnswer();
            Question q = ans.getQuestionAnswers();
            if (ans.getAnswerText().equalsIgnoreCase(q.getCorrectAnswer())){
                correctAnswers++;
            }
        }
        if (correctAnswers > 0){
            percentage = (correctAnswers/totalQuestions) * 10;
        }
        return percentage;
    }
}
