package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.entity.Feedback;
import com.example.demo.entity.Maid;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.MaidRepository;
import com.example.demo.repository.UserLoginRepository;
import com.example.demo.service.FeedbackService;
@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	MaidRepository maidRepository;
	
	@Autowired
	UserLoginRepository userLoginRepository;
	@Override
	public Feedback addFeedback(Feedback f)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> op=userLoginRepository.findByUsername(f.getUsername());
		if(op.isPresent()) {
			f.setUser(op.get());
		Optional<Maid> m1=maidRepository.findById(f.getMaidid());
		if (m1.isPresent()){
		Maid maid1=maidRepository.findById(f.getMaidid()).get();	
		List<Feedback> list1=maid1.getReviews();
		
		f.setMaid(maid1);
		list1.add(f);
		maid1.setReviews(list1);
		if(maid1.getUserratings()==0) {
			maid1.setUserratings((maid1.getUserratings()+f.getRatings()));
		}	
		else {
			maid1.setUserratings((maid1.getUserratings()+f.getRatings())/2);
		}
		maidRepository.save(maid1);	
				return feedbackRepository.save(f);
		
		}else {
			throw new ResourceNotFoundException("Maid ","Maid id",f.getMaidid());
		}}
		else {
			throw new ResourceNotFoundException("User ","username",f.getUsername());
		}
	}

	@Override
	public List<Feedback> getallFeedbacks() {
		// TODO Auto-generated method stub
		return feedbackRepository.findAll();
	}

	@Override
	public void deleteFeedback(int id)throws ResourceNotFoundException {
		// TODO Auto-generated method stub

		Optional<Feedback> op=feedbackRepository.findById(id);
		if (op.isPresent()) {
			
		feedbackRepository.deleteById(id);
		}
		else {
			throw new ResourceNotFoundException("Feedback ","feedback id",id);
		}		
	}

	@Override
	public Feedback findFeedbackbyID(int id)throws ResourceNotFoundException {
		// TODO Auto-generated method stub

		Optional<Feedback> op=feedbackRepository.findById(id);
		if (op.isPresent()) {		
			 Feedback f1=feedbackRepository.findById(id).get();
			 return f1;
		}
		else {
			throw new ResourceNotFoundException("Feedback ","feedback id",id);	
			}
	}

	@Override
	public Feedback UpdateFeedbackbyID(int id, Feedback f)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Feedback> op = feedbackRepository.findById(id);
		if (op.isPresent()) {
			Feedback f1=feedbackRepository.findById(id).get();
			f1.setReview(f.getReview());
		return feedbackRepository.save(f1);
		}
		else{
			throw new ResourceNotFoundException("Feedback ","feedback id",id);
		}
	}

}
