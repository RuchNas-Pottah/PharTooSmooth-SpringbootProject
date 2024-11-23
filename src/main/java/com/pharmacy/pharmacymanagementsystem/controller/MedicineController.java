package com.pharmacy.pharmacymanagementsystem.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pharmacy.pharmacymanagementsystem.doa.MedRepo;
import com.pharmacy.pharmacymanagementsystem.doa.PrescMedRepo;
import com.pharmacy.pharmacymanagementsystem.doa.PrescriptionRepository;
import com.pharmacy.pharmacymanagementsystem.doa.CategoryRepo;
import com.pharmacy.pharmacymanagementsystem.doa.CustomerRepo;
import com.pharmacy.pharmacymanagementsystem.models.medicationStock;
import com.pharmacy.pharmacymanagementsystem.models.prescribed_medicines;
import com.pharmacy.pharmacymanagementsystem.service.MedicineService;
import com.pharmacy.pharmacymanagementsystem.models.IncreaseMedicationStock;
import com.pharmacy.pharmacymanagementsystem.models.IncreaseMedicineQuantity;
import com.pharmacy.pharmacymanagementsystem.models.Prescriptions;
import com.pharmacy.pharmacymanagementsystem.models.categories;

import org.springframework.ui.Model;

@Controller
public class MedicineController {
    int prescId;

    @Autowired
    private PrescriptionRepository prescRepo;

    @Autowired
    private CustomerRepo custRepo;

    @Autowired
    private PrescMedRepo prescribed_medicinesRepo;

    @Autowired
    private MedRepo medicationStockRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/addMedicine/{prescriptionId}")
    public String showAddMedicinesPage(@PathVariable String prescriptionId, Model model) {
        // Load the prescription and related data
        prescId=Integer.parseInt(prescriptionId);
        List<prescribed_medicines> prescription = prescribed_medicinesRepo.findByPrescriptionId(prescId);
        int custId=prescRepo.findCustId(prescId);
        String custEmail=custRepo.findEmail(custId);
        List<medicationStock> medicationStock = medicationStockRepo.findAll();
        List<categories> categories = categoryRepo.findAll();
        prescribed_medicines prescribed_medicines = new prescribed_medicines();
        model.addAttribute("custEmail", custEmail);
        model.addAttribute("prescribed_medicines", prescribed_medicines);
        model.addAttribute("prescription", prescription);
        model.addAttribute("medicationStock", medicationStock);
        model.addAttribute("categories", categories);
        model.addAttribute("quantity", new IncreaseMedicineQuantity());
        return "addMedicines";
    }

    @PostMapping("/saveMedicine/{medicationId}")
    public String addPrescription(@PathVariable String medicationId,@ModelAttribute prescribed_medicines prescribed_medicines,@ModelAttribute IncreaseMedicineQuantity quantity) {
        int medication_id=Integer.parseInt(medicationId);
        prescribed_medicines p=medicineService.getPrescribedMedicine(medication_id,prescId);
        if(p==null)
        {
            prescribed_medicines.setMedication_id(medication_id);
            prescribed_medicines.setPrescription_id(prescId);
            prescribed_medicinesRepo.save(prescribed_medicines);
        }
        else
        medicineService.AddMedicineToPrescription(medication_id,prescId , quantity, p);
        
        // System.out.println("saved medicine");
        // Redirect to the user's dashboard or any other appropriate page
        return "redirect:/addMedicine/" + prescId;
    }

}
