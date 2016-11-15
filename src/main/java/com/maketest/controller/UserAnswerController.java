package com.maketest.controller;

import com.maketest.dto.UserAnswerDTO;
import com.maketest.model.Result;
import com.maketest.model.UserAnswer;
import com.maketest.service.ResultService;
import com.maketest.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jovana Micic on 15-Nov-16.
 */
@RestController
@RequestMapping(value = "/api/useranswers")
public class UserAnswerController {

    @Autowired
    UserAnswerService userAnswerService;

    /* Saving all user answers for one test. */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<UserAnswerDTO>> saveUserAnswers(@RequestBody List<UserAnswerDTO> ualist){
        List<UserAnswerDTO> retVal = new ArrayList<>();

        for (UserAnswerDTO ua: ualist) {
            UserAnswer useranswer = new UserAnswer();
            useranswer.setUserAnswer(ua.getUserAnswer());
            useranswer = userAnswerService.save(useranswer);
            retVal.add(new UserAnswerDTO(useranswer));
        }
        return new ResponseEntity<>(retVal, HttpStatus.CREATED);
    }

}
