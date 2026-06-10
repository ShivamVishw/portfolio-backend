package com.shivam.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.portfolio.dto.ContactDto;
import com.shivam.portfolio.entity.ContactMessage;
import com.shivam.portfolio.repository.ContactRepository;
import com.shivam.portfolio.service.ContactService;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins= "http://localhost:4200")
public class ContactController {
	
	@Autowired
	private ContactService service;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@PostMapping
	public String saveContact(@RequestBody ContactDto dto) {
		service.saveMessage(dto);
		
		return "Message saved successfully";
	}
	
	@GetMapping("/messages")
	public List<ContactMessage> getAllMessages() {
	    return service.getAllMessages();
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMessage(@PathVariable Long id) {

	    service.deleteMessage(id);

	    return ResponseEntity.ok("Deleted Successfully");
	}
	

}
