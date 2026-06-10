package com.shivam.portfolio.service;

import java.util.List;

import com.shivam.portfolio.dto.ContactDto;
import com.shivam.portfolio.entity.ContactMessage;

public interface ContactService {
	
	void saveMessage(ContactDto dto);
	
	List<ContactMessage> getAllMessages();
	
	void deleteMessage(Long id);

}
