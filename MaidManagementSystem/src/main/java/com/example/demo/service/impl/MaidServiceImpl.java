package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Client;
import com.example.demo.entity.Maid;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.MaidRepository;
import com.example.demo.service.MaidService;


@Service
public class MaidServiceImpl implements MaidService {
	@Autowired
	MaidRepository maidRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ClientRepository clientRepository;

	@Override
	public Maid addMaid(Maid maid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Category> op1 = categoryRepository.findById(maid.getMaidcategoryid());
		if (op1.isPresent()) {
		Category c=categoryRepository.findById(maid.getMaidcategoryid()).get();
		maid.setCategory(c);
		Optional<Client> op2 = clientRepository.findById(maid.getMaidclientid());
		if (op2.isPresent()) {
		Client client=clientRepository.findById(maid.getMaidclientid()).get();
		maid.setClient(client);
		maid.setUserratings(0.0);
		return maidRepository.save(maid);
		}
		else {
			throw new ResourceNotFoundException("Client","Client Id",maid.getMaidclientid());
		}
	}
		else {
			throw new ResourceNotFoundException("Category","Category Id:",maid.getMaidcategoryid());
		}
	}

	@Override
	public List<Maid> findAllMaids() {
		// TODO Auto-generated method stub
		return maidRepository.findAll();

	}

	@Override
	public Maid updateMaid(int mid, Maid maid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Maid> op = maidRepository.findById(mid);
		if (op.isPresent()) {
			Maid _maid = maidRepository.findById(mid).get();

			Optional<Category> op1 = categoryRepository.findById(maid.getMaidcategoryid());
			if (op1.isPresent()) {
				Category c=categoryRepository.findById(maid.getMaidcategoryid()).get();
				_maid.setMaidcategoryid(maid.getMaidcategoryid());

			_maid.setCategory(c);
			}else {
				throw new ResourceNotFoundException("Category","Category Id",maid.getMaidcategoryid());
			}
			
			Optional<Client> op2 = clientRepository.findById(maid.getMaidclientid());
			if (op2.isPresent()) {
	
			Client client=clientRepository.findById(maid.getMaidclientid()).get();
			_maid.setClient(client);
			
			_maid.setMaidclientid(maid.getMaidclientid());
			}else
			{
				throw new ResourceNotFoundException("Client","ClientId",maid.getMaidclientid());
			}
			_maid.setName(maid.getName());
			_maid.setExperienceYears(maid.getExperienceYears());	
			_maid.setExpectedSalary(maid.getExpectedSalary());
			_maid.setPreferredCity(maid.getPreferredCity());
			_maid.setLanguagesKnown(maid.getLanguagesKnown());
			
			return maidRepository.save(_maid);
		} else {		
			throw new ResourceNotFoundException("Maid ","Maidd",mid);
		}
		
	}

	@Override
	public void deleteMaid(int mid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Maid> op = maidRepository.findById(mid);
		if (op.isPresent())

		{
			maidRepository.deleteById(mid);

		} 
		else 
		{
			throw new ResourceNotFoundException("Maid","maidid",mid);
		}		
	}

	@Override
	public Maid findMaidById(int mid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Maid> op = maidRepository.findById(mid);
		if (op.isPresent())

		{
			return maidRepository.findById(mid).get();

		} else {
			throw new ResourceNotFoundException("Maid","maidid",mid);
		}
	
	}

	@Override
	public List<Maid> findMaidbyName(String name) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		List<Maid> op = maidRepository.findMaidByNameContainingIgnoreCase(name);
		if (op.isEmpty())

		{
			throw new ResourceNotFoundException("Maids"," name",name);
			

		} else {
			return maidRepository.findMaidByNameContainingIgnoreCase(name);
		}
		}

	


}
