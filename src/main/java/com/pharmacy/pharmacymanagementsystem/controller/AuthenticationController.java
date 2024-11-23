package com.pharmacy.pharmacymanagementsystem.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pharmacy.pharmacymanagementsystem.models.User;
import com.pharmacy.pharmacymanagementsystem.service.AuthenticationService;
import com.pharmacy.pharmacymanagementsystem.service.MessageService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthenticationController extends Helper {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MessageService messageService;
    
     @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (authenticationService.isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("credentials", new User());
        return "login";
    }

     @PostMapping("/login")
    public String postLogin(@ModelAttribute User credentials, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        
        if (authenticationService.isAuthenticated(session)) {
            addDefaultAttributes(model, session);
            System.out.println(model.getAttribute("userRole"));
            if(model.getAttribute("userRole").equals("customer")){
                String x="redirect:dashboardu/" +authenticationService.getCurrentUser(session);
                System.out.println(x);
                return x;
            }
            else{
                return "redirect:admindashboard";
            }
        }

        String emailId = credentials.getUserEmail();
        String password = credentials.getPassword();
        String message=null;

        try {
            if (authenticationService.checkCredentials(emailId, password)) {
                authenticationService.loginUser(session, emailId);
                addDefaultAttributes(model, session);
                System.out.println(model.getAttribute("userRole"));
                messageService.redirectWithSuccess(redirectAttributes,"Successfully logged in");
                if(model.getAttribute("userRole").equals("customer")){
                    String x="redirect:dashboardu/" +authenticationService.getCurrentUser(session);
                    System.out.println(x);
                    return x;
                }
                else{
                    return "redirect:admindashboard";
                }
            }
            else{
                message="Incorrect password entered";
            }
        } catch (Exception e) {
            System.out.println(e);
            message="The Email ID does not belong to an account";
        }

        messageService.redirectWithError(redirectAttributes,message);
        model.addAttribute("credentials", credentials);
        System.out.println(message);
        return "redirect:login";
    }

    
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        authenticationService.logoutUser(session);
        return "redirect:/";
    }



}
