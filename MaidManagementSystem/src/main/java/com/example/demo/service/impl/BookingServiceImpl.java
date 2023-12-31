package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Maid;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.MaidRepository;
import com.example.demo.repository.UserLoginRepository;
import com.example.demo.service.BookingService;
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired 
	BookingRepository bookingRepository;
	
	@Autowired
	MaidRepository maidRepository;
	
	@Autowired
	UserLoginRepository userLoginRepository;
	@Override
	public String addBooking(Booking booking) throws ResourceAlreadyExistsException {
		// TODO Auto-generated method stub
		User u1=userLoginRepository.findById(booking.getClient_id()).get();
		Maid m1=maidRepository.findById(booking.getMaidbook_id()).get();
		if (m1.getSkills()==m1.getSkills())      //checks whether the quantity of books added by user is available in inventory.	
		{
		booking.setUser(u1);
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");  //generates Order Date and Time Automatically.
		String formattedDate = myDateObj.format(myFormatObj);
		booking.setBookingDate(formattedDate);
		
		booking.setMaid(m1);
		//booking.setTotalprice(b1.getPrice()*ot.getQuantity());
		booking.setBookingStatus("Booking Pending.");
		m1.setSkills(m1.getSkills());
		if(m1.getSkills() != null) {   //if the book stock runs out book will be deleted
			maidRepository.delete(m1);
		}
		maidRepository.save(m1);
		
		
		 bookingRepository.save(booking);
		 return "Your booking has been Placed Successfully.\n"
		 +"BookingId: "+booking.getBookingId()+"\nClient Name:"+u1.getFirstName()+
		 " "+u1.getLastName()+"\nEmail:"+u1.getEmail()+"\nMaid Id:"+booking.getMaidbook_id()+"\nMaid name:"
		 +m1.getName()+"\nBooking Date:"+booking.getBookingDate()+"\nBooking Status:"+booking.getBookingStatus()+"";
		}
		else {
			
			throw new ResourceAlreadyExistsException("the "+m1.getName()+" Maids are limited so you can only add "+m1.getSkills()
			+"maids to your booking");
		}
	}

	@Override
	public List<Booking> findAllBookings() {
		// TODO Auto-generated method stub
		return bookingRepository.findAll();
	}

	@Override
	public String updateBooking(int bid, Booking booking)throws ResourceAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	
	}

	@Override
	public void deleteBooking(int bid)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Booking> op = bookingRepository.findById(bid);
		if (op.isPresent()) {
			Booking b1=bookingRepository.findById(bid).get();
			Maid m1=maidRepository.findById(bid).get();
			//b1.setQuantityAvailable(ot.getQuantity()+b1.getQuantityAvailable());		//if an order is deleted the ordered books are sent back to the inventory stock.
			maidRepository.save(m1);
		bookingRepository.deleteById(bid);
		
		}
		else {
			throw new ResourceNotFoundException("Booking","id ",bid);

		}
		
	}

	@Override
	public String findBookingById(int bid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Booking> optional = bookingRepository.findById(bid);
		if (optional.isPresent()) {
			Booking b1=optional.get();
			User u1=userLoginRepository.findById(b1.getClient_id()).get();
			Maid m1=maidRepository.findById(b1.getMaidbook_id()).get();
//		return orderItemRepository.findById(otid).get();
			return "Your Booking Details: \n"
			 +"BookingId: "+b1.getBookingId()+"\nClient Name:"+u1.getFirstName()+
			 " "+u1.getLastName()+"\nEmail:"+u1.getEmail()+"\nMaid Id:"+m1.getMaidId()+"\nMaid name:"
			 +m1.getName()+"\nBooking Date:"+b1.getBookingDate()+"\nBooking Status:"+b1.getBookingStatus()+"";
		} else {
			throw new ResourceNotFoundException("Order","id ",bid);
		}
	}

}
