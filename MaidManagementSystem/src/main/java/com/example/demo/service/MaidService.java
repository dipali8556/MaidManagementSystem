package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Maid;
import com.example.demo.exception.ResourceNotFoundException;



public interface MaidService {
	public Maid addMaid(Maid maid) throws ResourceNotFoundException;

	public List<Maid> findAllMaids();

	public Maid updateMaid(int mid,Maid maid) throws ResourceNotFoundException;

	public void deleteMaid(int mid) throws ResourceNotFoundException;

	public Maid findMaidById(int mid) throws ResourceNotFoundException;

	public List<Maid> findMaidbyName(String name) throws ResourceNotFoundException;

}
