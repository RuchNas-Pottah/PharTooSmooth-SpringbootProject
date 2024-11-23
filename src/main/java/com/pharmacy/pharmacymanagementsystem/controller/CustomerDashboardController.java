package com.pharmacy.pharmacymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerDashboardController extends Helper {
     @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session, RedirectAttributes attributes)
    {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        return "dashboard";
    }
}