package com.maketest.controller;

import com.maketest.dto.TestDTO;
import com.maketest.model.Test;
import com.maketest.model.User;
import com.maketest.service.TestService;
import com.maketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jovana Micic on 08-Nov-16.
 */
@RestController
@RequestMapping(value = "/api/tests")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    UserService userService;

    /* Creating new test. */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TestDTO> addNewTest(@RequestBody TestDTO t, @RequestHeader("mtt") String token){
        User user = userService.getUserProfile(token);
        Test test = new Test();
        test.setTestName(t.getTestName());
        test.setDescription(t.getDescription());
        test.setCategory(t.getCategory());
        test.setUserTests(user);
        test.setCreatingDate(new Date());  //TODO dodati cuvanje pitanja
        test = testService.save(test);
        return new ResponseEntity<>(new TestDTO(test), HttpStatus.OK);
    }

    /* Updating test info.*/
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<TestDTO> updateTest(@RequestBody TestDTO t, @RequestHeader("mtt") String token){
        Test test = testService.findOne(t.getId());
        if(test == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        test.setTestName(t.getTestName());
        test.setDescription(t.getDescription());
        test.setCategory(t.getCategory());  //TODO dodati cuvanje pitanja
        testService.save(test);
        return new ResponseEntity<>(new TestDTO(test), HttpStatus.OK);
    }

    /* Getting all tests.*/
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<TestDTO>> getAllTests(){
        List<Test> tests = testService.findAll();
        List<TestDTO> testsDTO = new ArrayList<>();
        for (Test t : tests) {
            testsDTO.add(new TestDTO(t));
        }
        return new ResponseEntity<>(testsDTO, HttpStatus.OK);
    }

    /*
     * Getting test by id.
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<TestDTO> getTest(@PathVariable int id) {
        Test test = testService.findOne(id);
        if (test == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new TestDTO(test), HttpStatus.OK);
    }

}
