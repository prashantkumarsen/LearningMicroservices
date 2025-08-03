package com.question.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.entities.Question;
import com.question.services.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController 
{
	@Autowired
	private QuestionService questionService;
	
	@PostMapping
	public ResponseEntity<Question> create(@RequestBody Question question)
	{
		Question question2 = questionService.createQuestion(question);
		return new ResponseEntity<>(question2,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody Question question)
	{
		Question updateQuestion = questionService.updateQuestion(id, question);
		return new ResponseEntity<>(updateQuestion,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Question>> getAll()
	{
		List<Question> all = questionService.getAll();
		return new ResponseEntity<>(all,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Question> getSingle(@PathVariable Long id)
	{
		Question single = questionService.getSingle(id);
		return new ResponseEntity<Question>(single,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id)
	{
		questionService.deleteQuestion(id);
	}
	
	//get all questions of specific quiz
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<List<Question>> getQuestionsOfQuiz(@PathVariable Long quizId)
	{
		List<Question> questionOfQuiz = questionService.getQuestionOfQuiz(quizId);
		return new ResponseEntity<>(questionOfQuiz,HttpStatus.OK);
	}
	
}
