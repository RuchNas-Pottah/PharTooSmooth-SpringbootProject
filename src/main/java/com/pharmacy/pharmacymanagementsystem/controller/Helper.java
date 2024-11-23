package com.pharmacy.pharmacymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.pharmacy.pharmacymanagementsystem.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;

abstract class Helper {
    
    @Autowired
    private AuthenticationService authenticationService;

    public void addDefaultAttributes(Model model,HttpSession session)
    {
        String emailId = authenticationService.getCurrentUser(session);
        if (emailId != null) {
            model.addAttribute("emailId", emailId);
            model.addAttribute("userImageUrl", "https://ui-avatars.com/api/?name=" + emailId);

            String userRole = authenticationService.getRole(emailId);
            model.addAttribute("userRole", userRole);
        }
    }
    public Boolean isAuthenticated(HttpSession session) {
        return authenticationService.isAuthenticated(session);
    }

}
