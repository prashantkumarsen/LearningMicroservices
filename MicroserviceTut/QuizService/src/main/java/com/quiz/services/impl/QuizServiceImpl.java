package com.quiz.services.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quiz.entities.Quiz;
import com.quiz.exception.ResourceNotFoundException;
import com.quiz.repository.QuizRepository;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService
{
	@Autowired
	private QuizRepository quizRepository;
	
	@Autowired
	private QuestionClient questionClient;
	
	@Override
	public Quiz createQuiz(Quiz quiz) 
	{
		Quiz saveQuiz = quizRepository.save(quiz);
		return saveQuiz;
	}
	
	@Override
	public Quiz updateQuiz(Long id, Quiz quiz)
	{
		Quiz existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        existingQuiz.setTitle(quiz.getTitle());
        existingQuiz.setDescription(quiz.getDescription());

        return quizRepository.save(existingQuiz);	  
	}

	@Override
	public List<Quiz> getAllQuizzes() 
	{		List<Quiz> quizzes = quizRepository.findAll();
	        List<Quiz> newQuizzes = quizzes.stream().map(quiz -> {
	        	quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
	        	return quiz;
	        }).collect(Collectors.toList());
		return newQuizzes; 
	}

	@Override
	public Quiz getSingleQuiz(Long id) 
	{
	  Quiz quiz	=quizRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quiz not frund with given Id !!"));
	  quiz.setQuestions(questionClient.getQuestionOfQuiz(id));
	  return quiz;
	}

	@Override
	public void deleteQuiz(Long id) 
	{
		quizRepository.deleteById(id);
	}
}
