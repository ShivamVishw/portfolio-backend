package com.shivam.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.portfolio.entity.ContactMessage;

public interface ContactRepository extends JpaRepository<ContactMessage, Long>{

}
