package com.shivam.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.portfolio.dto.ContactDto;
import com.shivam.portfolio.entity.ContactMessage;
import com.shivam.portfolio.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{
	
	
	@Autowired
	private ContactRepository repository;

	@Override
	public void saveMessage(ContactDto dto) {
		// TODO Auto-generated method stub
		
		ContactMessage message = new ContactMessage(); 
		
		message.setName(dto.getName());
		message.setEmail(dto.getEmail());
		message.setSubject(dto.getSubject());
		message.setMessage(dto.getMessage());
		
		repository.save(message);
		
	}

	@Override
	public List<ContactMessage> getAllMessages() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	@Override
	public void deleteMessage(Long id) {
	    repository.deleteById(id);
	}
	

}
