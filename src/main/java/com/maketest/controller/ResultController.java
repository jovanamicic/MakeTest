package com.maketest.controller;

import com.maketest.dto.ResultDTO;
import com.maketest.model.Result;
import com.maketest.model.UserAnswer;
import com.maketest.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Jovana Micic on 15-Nov-16.
 */
@RestController
@RequestMapping(value = "/api/results")
public class ResultController {

    @Autowired
    ResultService resultService;

    /* This function save result*/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResultDTO> saveResult(@RequestBody ResultDTO r){
        Set<UserAnswer> userAnswerList = r.getUserAnswerResult();
        int percentage = resultService.calculate(userAnswerList);
        Result result = new Result();
        result.setResultDate(new Date());
        result.setPercentage(percentage);
        result.setTestResult(r.getTest());
        result.setUserAnswerResult(r.getUserAnswerResult());
        result = resultService.save(result);

        return new ResponseEntity<>(new ResultDTO(result), HttpStatus.CREATED);
    }
}
