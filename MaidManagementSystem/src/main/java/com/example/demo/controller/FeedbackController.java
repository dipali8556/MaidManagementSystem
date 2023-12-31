package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Feedback;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.impl.FeedbackServiceImpl;

import jakarta.validation.Valid;


@RestController
public class FeedbackController {
	@Autowired
	FeedbackServiceImpl feedbackServiceImpl;
	
	
	
	@GetMapping("/feedback")				//method for fetching all the Feedbacks.
	public List<Feedback> getAllFeedbacks(){
		return feedbackServiceImpl.getallFeedbacks();
	}
	
	@PostMapping("/feedback")				//Method for Adding a Feedback.
	public ResponseEntity<Object> addnewFeedback(@RequestBody @Valid Feedback f) throws ResourceNotFoundException{
		Feedback savedFeedback = feedbackServiceImpl.addFeedback( f);
		
		return new ResponseEntity<Object>(savedFeedback, HttpStatus.CREATED);
	}
	
	@GetMapping("/feedback/{id}")			//Method for Fetching a feedback Using its id.
	public Feedback FindFeedbackByid(@PathVariable int id) throws ResourceNotFoundException {
		return feedbackServiceImpl.findFeedbackbyID(id);
	}
	
	@PutMapping("/feedback/{id}")			//Method for Updating a Feedback Using its Id.
	public Feedback updateFeedbackbyId(@PathVariable int id,@RequestBody @Valid Feedback f) throws ResourceNotFoundException{
		return feedbackServiceImpl.UpdateFeedbackbyID(id,f);
		}
	
	@DeleteMapping("/feedback/{id}")		//Method for Deleting a Feedback Using its id.
	public String deleteFeedbackbyId(@PathVariable int id) throws ResourceNotFoundException {
		feedbackServiceImpl.deleteFeedback(id);
		return "Feedback is deleted.";
	}

}
