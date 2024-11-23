package com.pharmacy.pharmacymanagementsystem.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pharmacy.pharmacymanagementsystem.doa.CustomerRepo;
import com.pharmacy.pharmacymanagementsystem.doa.MedRepo;
import com.pharmacy.pharmacymanagementsystem.doa.OrderRepo;
import com.pharmacy.pharmacymanagementsystem.doa.PrescMedRepo;
import com.pharmacy.pharmacymanagementsystem.doa.PrescriptionRepository;
import com.pharmacy.pharmacymanagementsystem.models.medicationStock;
import com.pharmacy.pharmacymanagementsystem.models.orders;
import com.pharmacy.pharmacymanagementsystem.models.prescribed_medicines;
import com.pharmacy.pharmacymanagementsystem.service.AdminService;


@Controller
public class OrdersController{
    int prescriptionId;
    int custId;

    @Autowired
    private PrescMedRepo prescMedRepo;

    @Autowired
    private MedRepo medicationStockRepo;

    @Autowired
    private PrescriptionRepository prescRepo;

    @Autowired
    private CustomerRepo custRepo;

    @Autowired
    private OrderRepo orderRepo;

    

    @GetMapping("/cart/{prescId}")
    public String showcart(@PathVariable String prescId,Model model){
        prescriptionId=Integer.parseInt(prescId);
        custId=prescRepo.findCustId(prescriptionId);
        String custAdress=custRepo.findAddress(custId);
        List<prescribed_medicines> presc_med= prescMedRepo.findByPrescriptionId(prescriptionId);
        List<medicationStock> medicationStock = medicationStockRepo.findAll();
        int totalAmount = 0;
        for (prescribed_medicines medicine : presc_med) {
            totalAmount += medicationStockRepo.findPriceById(medicine.getMedication_id()) * medicine.getQuantity();
            }
        
        model.addAttribute("custAdress", custAdress);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("presc_med", presc_med);
        model.addAttribute("medicationStock", medicationStock);
        return "cart";
    }

    @PostMapping("/submitOrder")
    public String submitorder(@RequestParam("order_date") String orderDate, @RequestParam("total_amount") Integer totalAmount, @RequestParam("address") String address) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(orderDate);
            
            List<prescribed_medicines> presc_med= prescMedRepo.findByPrescriptionId(prescriptionId);
            List<medicationStock> medicationStock = medicationStockRepo.findAll();
            for (prescribed_medicines medicine : presc_med) {
                for (medicationStock medStock : medicationStock) {
                    if (medStock.getId() == medicine.getMedication_id()) {
                        medStock.setStock_quantity(medStock.getStock_quantity() - medicine.getQuantity());
                        medicationStockRepo.updateMedicationStock(medStock);
                        break;
            }

        }
            }
           

            // Create an 'orders' object and set the properties
            orders order = new orders();
            order.setOrder_date(parsedDate);
            order.setTotal_amount(totalAmount);
            order.setAddress(address);
            order.setCustomer_id(custId);
            order.setPrescription_id(prescriptionId);
            
            // Save the order
            orderRepo.save(order);
           
        
            return "redirect:/profile"; // Redirect to a success page
        } catch (Exception e) {
            // Handle any exceptions here
            return "redirect:/error"; // Redirect to an error page
        }
    }
}