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

import com.example.demo.entity.Category;
import com.example.demo.entity.Client;
import com.example.demo.entity.Maid;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.impl.BookingServiceImpl;
import com.example.demo.service.impl.CategoryServiceImpl;
import com.example.demo.service.impl.ClientServiceImpl;
import com.example.demo.service.impl.MaidServiceImpl;

import jakarta.validation.Valid;



@RestController
public class MaidController {
	@Autowired
	MaidServiceImpl maidServiceImpl;
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	ClientServiceImpl clientServiceImpl;
	
	@Autowired
	BookingServiceImpl bookingServicempl;

	@GetMapping("/maids")				//Method for Fetching all Maids.
	public List<Maid> findAllMaids() {
		return maidServiceImpl.findAllMaids();
		
	}
	
	@PostMapping("/maids")					//Method for adding new Book
	public ResponseEntity<Object> addMaid(@RequestBody @Valid Maid maid) throws ResourceNotFoundException{
		
		// Ensure that the book's category and publisher are set correctly before adding
		
		Maid maid1 = maidServiceImpl.addMaid(maid);				
		maid1.setCategory(maid.getCategory());
		maid1.setClient(maid.getClient());
		maidServiceImpl.addMaid(maid1);
		return new ResponseEntity<>(maid1, HttpStatus.CREATED);
	}	

	@GetMapping("/maids/{id}")				//Method for Fetching a Book using its Id.
	public Maid FindMaidByMaidid(@PathVariable int id) throws ResourceNotFoundException {
		return maidServiceImpl.findMaidById(id);
	}

	@GetMapping("/maid/{name}")				//Method for Fetching a Book using its Title.
	public List<Maid> FindMaidByNmae(@PathVariable String name) throws ResourceNotFoundException{
		return maidServiceImpl.findMaidbyName(name);
	}

	@DeleteMapping("/maids/{id}")					//Method for Deleteing a Book using its Id.
	public String deleteMaid(@PathVariable int id) throws ResourceNotFoundException {
		
			maidServiceImpl.deleteMaid(id);
			return "Maid deleted Successfully.";
	}
	
	@PutMapping("/maids/{mid}")						//Method for Updating a Book using its Id.
	public Maid updateMaid(@PathVariable int mid,@RequestBody @Valid Maid maid) throws ResourceNotFoundException
	{
		// Ensure that the book's category and publisher are set correctly before updating
		Category c=categoryServiceImpl.findCategoryById(maid.getMaidcategoryid());
		Client client=clientServiceImpl.findClientById(maid.getMaidclientid());
		
		return maidServiceImpl.updateMaid(mid, maid);
		
	}
	


}
