package com.pharmacy.pharmacymanagementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class MessageService {

    public void displayError(Model model, String message) {
        model.addAttribute("errorToast", message);
    }

    public void displayInfo(Model model, String message) {
        model.addAttribute("infoToast", message);
    }

    public void displaySuccess(Model model, String message) {
        model.addAttribute("successToast", message);
    }

    public void redirectWithError(RedirectAttributes attributes, String message) {
        attributes.addFlashAttribute("errorToast", message);
    }

    public void redirectWithSuccess(RedirectAttributes attributes, String message) {
        attributes.addFlashAttribute("successToast", message);
    }

    public void redirectWithInfo(RedirectAttributes attributes, String message) {
        attributes.addFlashAttribute("infoToast", message);
    }
    
}
