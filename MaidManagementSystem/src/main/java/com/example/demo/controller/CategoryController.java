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
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.impl.CategoryServiceImpl;

import jakarta.validation.Valid;



@RestController
public class CategoryController {
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping("/category")				//Method for Fetching All the Categories.
	public List<Category> findAllCategorys() {
		return categoryServiceImpl.findAllCategorys();
	}

	@PostMapping("/category")				//Method for Adding a new Category.
	public ResponseEntity<Category> AddNewCategory(@RequestBody @Valid Category c) throws ResourceAlreadyExistsException {
		
		Category c1=categoryServiceImpl.addCategory(c);
		
		return new ResponseEntity<>(c1, HttpStatus.CREATED);
	}

	@GetMapping("/category/{id}")				//Method for Fetching a the Category using its Id.
	public Category findCategoryByCategoryid(@PathVariable int id) throws ResourceNotFoundException {
		return categoryServiceImpl.findCategoryById(id);
	}

	@PutMapping("/category/{cid}")				//Method for Updating a Category using its Id.
	public Category updateCategory(@PathVariable int cid,@RequestBody @Valid Category c) throws ResourceNotFoundException{
	return categoryServiceImpl.updateCategory(c, cid);
	}
	
	@DeleteMapping("/category/{id}")			//Method for Deleting a Category using its Id.
	public String deleteCategory(@PathVariable int id) throws ResourceNotFoundException {
		categoryServiceImpl.deleteCategory(id);
		return "Category is deleted";
	}
}
