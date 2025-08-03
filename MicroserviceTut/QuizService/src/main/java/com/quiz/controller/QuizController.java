package com.quiz.controller;

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
import com.quiz.entities.Quiz;
import com.quiz.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController 
{
	@Autowired
	private QuizService quizService;
	
	//create a quiz
	@PostMapping
	public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) 
	{
		Quiz quiz2 = quizService.createQuiz(quiz);
		return new ResponseEntity<>(quiz2,HttpStatus.CREATED);		
	}
	
	//update a quiz
	@PutMapping("/{id}")
	public ResponseEntity<Quiz> updateQuiz(
			@PathVariable Long id, 
			@RequestBody Quiz quiz)
	{
		Quiz updatedQuiz = quizService.updateQuiz(id, quiz);
        return ResponseEntity.ok(updatedQuiz);
	}
	
	//get all quizzes
	@GetMapping
	public ResponseEntity<List<Quiz>> getAllQuiz()
	{
		List<Quiz> allQuizzes = quizService.getAllQuizzes();
		return new ResponseEntity<>(allQuizzes,HttpStatus.OK);
	}
	//get a single quiz
	@GetMapping("/{id}")
	public ResponseEntity<Quiz> getSingleQuiz(@PathVariable Long id)
	{
		Quiz singleQuiz = quizService.getSingleQuiz(id);
		return new ResponseEntity<>(singleQuiz,HttpStatus.OK);			
	}
		
	//delete a quiz
	@DeleteMapping("/{id}")
	public void deleteQuiz(@PathVariable Long id)
	{
		quizService.deleteQuiz(id);
	}
}
