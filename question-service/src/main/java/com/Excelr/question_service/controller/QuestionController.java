package com.Excelr.question_service.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Excelr.question_service.model.Question;
import com.Excelr.question_service.model.QuestionWrapper;
import com.Excelr.question_service.model.Response;
import com.Excelr.question_service.service.QuestionService;

import org.springframework.core.env.Environment;


@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    Environment environment;
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return  questionService.addQuestion(question);
    }

    //generate quiz from question service

    @GetMapping("generate")
    public ResponseEntity <List<Integer>> getQuestionsForQuiz
    (@RequestParam String categoryName,@RequestParam Integer numQuestions) {
        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }
    
    //getQuestions(Question id)
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }
    

    //getScore()

    @PostMapping("getScore")
    public ResponseEntity <Integer> getScore(@RequestBody List<Response> responses) {
        //TODO: process POST request
        
        return questionService.getScore(responses);
    }
    


}
