package com.question.services;

import java.util.List;

import com.question.entities.Question;

public interface QuestionService 
{
	Question createQuestion(Question question);
	
	Question updateQuestion(Long id, Question question);
	
	List<Question> getAll();
	
	Question getSingle(Long id);
	
    void deleteQuestion(Long id);
    
    List<Question> getQuestionOfQuiz(Long quizId);
}
