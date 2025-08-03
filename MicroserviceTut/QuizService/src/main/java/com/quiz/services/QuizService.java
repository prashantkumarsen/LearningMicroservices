package com.quiz.services;

import java.util.List;

import com.quiz.entities.Quiz;

public interface QuizService 
{
	Quiz createQuiz(Quiz quiz);
	
	Quiz updateQuiz(Long id, Quiz quiz);
	
	List<Quiz> getAllQuizzes();
	
	Quiz getSingleQuiz(Long id);
	
	void deleteQuiz(Long id);
}
