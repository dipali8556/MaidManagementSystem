package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Feedback;
import com.example.demo.exception.ResourceNotFoundException;


public interface FeedbackService {
	public Feedback addFeedback(Feedback f) throws ResourceNotFoundException;
	
	public List<Feedback> getallFeedbacks();

	public void deleteFeedback(int id) throws ResourceNotFoundException;
	
	public Feedback findFeedbackbyID(int id) throws ResourceNotFoundException;
	
	public Feedback UpdateFeedbackbyID(int id,Feedback f) throws ResourceNotFoundException;

}
