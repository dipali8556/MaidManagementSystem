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

import com.example.demo.entity.Client;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.impl.ClientServiceImpl;

import jakarta.validation.Valid;



@RestController
public class ClientController {
	@Autowired
	ClientServiceImpl clientServiceImpl;
	
	@GetMapping("/client")			//Method for Fetching All Publishers.
	public List<Client> findAllClients() {
		return clientServiceImpl.findAllClients();
	}

	@PostMapping("/client")			//Method for adding a new Publisher.
	public ResponseEntity<Object> addNewClient(@RequestBody @Valid Client client) throws ResourceAlreadyExistsException {
		Client c1=clientServiceImpl.addClient(client);
		return new ResponseEntity<Object>(c1,HttpStatus.ACCEPTED);
	}

	@GetMapping("/client/{id}")		//Method for Fetching a Publisher using its id.
	public Client findClientByClientid(@PathVariable int id) throws ResourceNotFoundException {
		return clientServiceImpl.findClientById(id);
	}

	@PutMapping("/client/{pid}")			//Method for Updating a Publisher using its id.
	public Client updateClient(@PathVariable int cid,@RequestBody @Valid Client client) throws ResourceNotFoundException{
	return clientServiceImpl.updateClient(client,cid);
	}
	
	@DeleteMapping("/client/{id}")		//Method for Deleting a Publisher using its id.
	public String deleteClient(@PathVariable int id) throws ResourceNotFoundException {
		clientServiceImpl.deleteClient(id);
		return "Client is delted";
	}	

}
