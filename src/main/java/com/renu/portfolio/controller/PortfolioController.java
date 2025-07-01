package com.renu.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.renu.portfolio.model.Contact;
import com.renu.portfolio.service.ContactService;

import jakarta.validation.Valid;

@Controller
public class PortfolioController {
    @Autowired
    private ContactService contactService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contact", new Contact());
        return "index";
    }
    
    @PostMapping("/contact")
    public String submitContact(@Valid @ModelAttribute("contact") Contact contact, 
                              BindingResult result, 
                              RedirectAttributes redirectAttributes,
                              Model model) {
        
        if (result.hasErrors()) {
            return "index";
        }
        
        try {
            contactService.saveContact(contact);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Thank you for your message! I'll get back to you soon.");
            return "redirect:/#contact";
        } catch (Exception e) {
            model.addAttribute("errorMessage", 
                "There was an error sending your message. Please try again.");
            return "index";
        }
    }
    
    @GetMapping("/admin/contacts")
    public String viewContacts(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        return "admin/contacts";
    }
    
    @GetMapping("/resume")
    public String downloadResume() {
        // This would serve the resume file
        return "redirect:/files/Renuka_Sunkara_Data_Scientist.pdf";
    }
}
