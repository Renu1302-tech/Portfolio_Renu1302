package com.renu.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.renu.portfolio.model.Contact;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
    
    @Query("SELECT c FROM Contact c ORDER BY c.createdAt DESC")
    List<Contact> findAllOrderByCreatedAtDesc();
    
    List<Contact> findByEmailContainingIgnoreCase(String email);
    
    List<Contact> findBySubjectContainingIgnoreCase(String subject);
}
