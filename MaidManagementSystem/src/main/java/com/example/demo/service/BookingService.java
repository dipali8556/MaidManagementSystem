package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Booking;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;



public interface BookingService {

	public String addBooking(Booking booking) throws ResourceAlreadyExistsException;

	public List<Booking> findAllBookings();

	public String updateBooking(int bid,Booking booking) throws ResourceAlreadyExistsException;

	public void deleteBooking(int bid) throws ResourceNotFoundException;

	public String findBookingById(int bid) throws ResourceNotFoundException;
	
	

}
