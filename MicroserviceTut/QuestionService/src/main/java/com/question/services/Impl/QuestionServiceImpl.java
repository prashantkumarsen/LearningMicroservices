package com.question.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.question.entities.Question;
import com.question.repository.QuestionRepository;
import com.question.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService
{
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question createQuestion(Question question) 
	{
		Question question2 = questionRepository.save(question);
		return question2;		
	}

	@Override
	public Question updateQuestion(Long id, Question question) 
	{
		Question existingQuestion = questionRepository.findById(id).orElseThrow(()-> new RuntimeException("Question not found"));
		existingQuestion.setQuestionText(question.getQuestionText());
		existingQuestion.setQuizId(question.getQuizId());
		return questionRepository.save(existingQuestion);
	}

	@Override
	public List<Question> getAll() {
		List<Question> FeatchAll = questionRepository.findAll();
		return FeatchAll;
	}

	@Override
	public Question getSingle(Long id) 
	{
		return questionRepository.findById(id).orElseThrow(()-> new RuntimeException("Question not found"));
	}

	@Override
	public void deleteQuestion(Long id)
	{
		questionRepository.deleteById(id);
	}

	@Override
	public List<Question> getQuestionOfQuiz(Long quizId) 
	{		
		return questionRepository.findByQuizId(quizId);
	}

}
