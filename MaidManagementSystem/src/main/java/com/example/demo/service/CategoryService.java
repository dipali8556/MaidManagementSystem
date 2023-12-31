package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;



public interface CategoryService {
	public Category addCategory(Category c) throws ResourceAlreadyExistsException;

	public List<Category> findAllCategorys();

	public Category updateCategory(Category c,int cid) throws ResourceNotFoundException;

	public void deleteCategory(int cid) throws ResourceNotFoundException;

	public Category findCategoryById(int cid) throws ResourceNotFoundException;

}
