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

import com.example.demo.entity.Booking;
import com.example.demo.entity.Maid;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.impl.BookingServiceImpl;
import com.example.demo.service.impl.MaidServiceImpl;

import jakarta.validation.Valid;



@RestController
public class BookingController {
	@Autowired
	BookingServiceImpl bookingServiceImpl;
	
	@Autowired
	MaidServiceImpl maidServiceImpl;
	
	
	
	
	
	@GetMapping("/bookings")				//Method for Fetching all the orders.
	public List<Booking> findBookings() {
		return bookingServiceImpl.findAllBookings();
	}

	@PostMapping("/bookings")				//Method for Placing an order.
	public ResponseEntity<Object> AddNewBooking(@RequestBody @Valid Booking b) throws ResourceNotFoundException, ResourceAlreadyExistsException {
		Maid maid=maidServiceImpl.findMaidById(b.getMaidbook_id());
		
		//User c=myUserDetailsService.getUserById(d.getCust_id());
		
		String booking=bookingServiceImpl.addBooking(b);
		return new ResponseEntity<Object>(booking,HttpStatus.ACCEPTED);
	}

	@GetMapping("/bookings/{id}")			//Method for Fetching the orders using its Id.
	public String FindBookingByBookingid(@PathVariable int id) throws ResourceNotFoundException {
		return bookingServiceImpl.findBookingById(id);
		
	}

	@PutMapping("/bookings/{id}")			//Method for Updating the orders using its Id.
	public String UpdateBooking(@RequestBody @Valid Booking booking,@PathVariable int id) throws ResourceNotFoundException, ResourceAlreadyExistsException {
		Maid maid=maidServiceImpl.findMaidById(booking.getMaidbook_id());
		
		//User c=myUserDetailsService.getUserById(ot.getCust_id());
		
		return bookingServiceImpl.updateBooking(id, booking);
		
	}
	

	@DeleteMapping("/bookings/{id}")				//Method for Deleting the orders using its Id.
	public String deleteBooking(@PathVariable int id) throws ResourceNotFoundException {
		if (bookingServiceImpl.findBookingById(id)!=null){
			bookingServiceImpl.deleteBooking(id);
			return "Booking deleted Successfully.";
		}
		else {
		return "Booking is not present on this id.";
		}
	}
	
	
}
