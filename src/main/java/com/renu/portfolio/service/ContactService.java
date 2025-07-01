package com.renu.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.renu.portfolio.model.Contact;
import com.renu.portfolio.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
    
    public List<Contact> getAllContacts() {
        return contactRepository.findAllOrderByCreatedAtDesc();
    }
    
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }
    
    public List<Contact> getContactsByEmail(String email) {
        return contactRepository.findByEmailContainingIgnoreCase(email);
    }
    
    public List<Contact> getContactsBySubject(String subject) {
        return contactRepository.findBySubjectContainingIgnoreCase(subject);
    }
    
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
