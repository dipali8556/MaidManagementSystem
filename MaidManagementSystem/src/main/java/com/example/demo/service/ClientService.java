package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Client;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;



public interface ClientService {

	public Client addClient(Client client) throws ResourceAlreadyExistsException;

	public List<Client> findAllClients();

	public Client updateClient(Client client,int cid) throws ResourceNotFoundException;

	public void deleteClient(int cid) throws ResourceNotFoundException;

	public Client findClientById(int cid) throws ResourceNotFoundException;
}
