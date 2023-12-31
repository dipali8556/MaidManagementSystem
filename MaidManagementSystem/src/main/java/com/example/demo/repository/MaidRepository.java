package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Maid;

@Repository
public interface MaidRepository extends JpaRepository<Maid,Integer> {
	public List<Maid> findMaidByNameContainingIgnoreCase(String name);
	public Optional<Maid> findMaidByname(String name);
	

}
