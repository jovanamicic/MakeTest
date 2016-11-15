package com.maketest.controller;

import com.maketest.dto.QuestionDTO;
import com.maketest.model.Answer;
import com.maketest.model.Question;
import com.maketest.model.Test;
import com.maketest.service.AnswerService;
import com.maketest.service.QuestionService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jovana Micic on 09-Nov-16.
 */
@RestController
@RequestMapping(value = "/api/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    /* Method creates new question.  */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<QuestionDTO> addNewQuestion(@RequestBody QuestionDTO q, @RequestHeader("mtt") String token){
        Question question = new Question();
        question.setQuestionText(q.getQuestionText());
        question.setCorrectAnswer(q.getCorrectAnswer());
        question = questionService.save(question);
        //creating answers
        Set<Answer> savedAnswers = new HashSet<>();
        Set<Answer> answers = q.getAnswers();
        for (Answer a : answers) {
            Answer ans = new Answer();
            ans.setAnswerText(a.getAnswerText());
            ans.setQuestionAnswers(question);
            ans = answerService.save(ans);
            savedAnswers.add(ans);
        }
        question.setAnswers(savedAnswers);

        question = questionService.save(question);
        return new ResponseEntity<>(new QuestionDTO(question), HttpStatus.OK);
    }

    /* Method updates question*/
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<QuestionDTO> updateQuestion(@RequestBody QuestionDTO q, @RequestHeader("mtt") String token){
        Question question = questionService.findOne(q.getId());
        if (question == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        question.setQuestionText(q.getQuestionText());
        question.setCorrectAnswer(q.getCorrectAnswer());
        question = questionService.save(question);
        return new ResponseEntity<>(new QuestionDTO(question), HttpStatus.OK);
    }

    /* Function returns all questions of given test id.*/
    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<QuestionDTO>> getTestQuestions(@PathVariable int id){
        List<QuestionDTO> retVal = new ArrayList<>();
        List<Question> questions = questionService.findByTest(id);
        for (Question q: questions) {
            retVal.add(new QuestionDTO(q));
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }



























}
