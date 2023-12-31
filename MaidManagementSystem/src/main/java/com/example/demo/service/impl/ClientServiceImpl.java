package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	ClientRepository clientRepository;

	@Override
	public Client addClient(Client client)throws ResourceAlreadyExistsException  {
        // Method to add a new publisher

		Optional<Client> op1 = clientRepository.findByName(client.getName());
		if(op1.isEmpty()) {
		Optional<Client> optional = clientRepository.findBycontactNumber(client.getContactNumber());
		if (optional.isPresent()) {
			throw new ResourceAlreadyExistsException("Publisher already Exists with the same Mobile Number "+client.getContactNumber()
			+" Kindly choose a Different Number.");
		}		else {
			return clientRepository.save(client);

		}	}
		else {
			throw new ResourceAlreadyExistsException("Publisher already Exists with the same Name "+client.getName());

		}
	}

	@Override
	public List<Client> findAllClients() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

	@Override
	public Client updateClient(Client client, int cid)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
        // Method to update an existing client

		Optional<Client> op = clientRepository.findById(cid);
		if (op.isPresent()) {
			Client client1=clientRepository.findById(cid).get();
			client1.setName(client.getName());
			client1.setAddress(client.getAddress());
			client1.setContactNumber(client.getContactNumber());
			
			return clientRepository.save(client1);
		}else{
			System.out.println("Publisher record is not present in this id :"+cid);
			throw new ResourceNotFoundException("Client","id",cid);
		}
	}

	@Override
	public void deleteClient(int cid)throws ResourceNotFoundException {
	       // Method to delete a Client

			Optional<Client> op = clientRepository.findById(cid);
			if (op.isPresent())
			{
				clientRepository.deleteById(cid);
			} else {
				throw new ResourceNotFoundException("Client","id",cid);
			}
		
	}

	@Override
	public Client findClientById(int cid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
	       // Method to retrieve a client by its ID

			Optional<Client> optional = clientRepository.findById(cid);
			if (optional.isPresent()) {
			return clientRepository.findById(cid).get();
			} else {
				throw new ResourceNotFoundException("Client","id",cid);
			}
	}

}
