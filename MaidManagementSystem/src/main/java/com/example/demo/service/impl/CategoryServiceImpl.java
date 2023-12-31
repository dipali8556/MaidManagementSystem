package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category c)throws ResourceAlreadyExistsException {
		// TODO Auto-generated method stub
		Optional<Category> op=categoryRepository.findByName(c.getName());
		if (op.isPresent()) {
			throw new ResourceAlreadyExistsException("Category already exists with the name"+c.getName());
		}		else {
			return categoryRepository.save(c);
			}
	}

	@Override
	public List<Category> findAllCategorys() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategory(Category c, int cid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
        // Method to update a category by its ID

		Optional<Category> op = categoryRepository.findById(cid);
		if (op.isPresent()) {
			Category c1=categoryRepository.findById(cid).get();
			c1.setName(c.getName());
			
			return categoryRepository.save(c1);
		}else{

			throw new ResourceNotFoundException("Category ","Category id",cid);
		}

	}

	@Override
	public void deleteCategory(int cid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Category> op = categoryRepository.findById(cid);
		if (op.isPresent())

		{
			categoryRepository.deleteById(cid);

		} else {
			throw new ResourceNotFoundException("Category ","Category id",cid);
		}		
	}

	@Override
	public Category findCategoryById(int cid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Category> optional = categoryRepository.findById(cid);
		if (optional.isPresent()) {
			return categoryRepository.findById(cid).get();
		} else {
			throw new ResourceNotFoundException("Category ","Category id",cid);
		}
	}

}
