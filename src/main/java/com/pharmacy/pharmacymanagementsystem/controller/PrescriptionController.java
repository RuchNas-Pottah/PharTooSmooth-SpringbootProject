package com.pharmacy.pharmacymanagementsystem.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.pharmacymanagementsystem.doa.CustomerRepo;
import com.pharmacy.pharmacymanagementsystem.doa.OrderRepo;
import com.pharmacy.pharmacymanagementsystem.doa.PrescriptionRepository;
import com.pharmacy.pharmacymanagementsystem.models.Prescriptions;
import com.pharmacy.pharmacymanagementsystem.models.orders;
import com.pharmacy.pharmacymanagementsystem.service.AdminService;
import com.pharmacy.pharmacymanagementsystem.models.Customer;
@Controller
public class PrescriptionController {
    String email;
    int customer_id;
    @Autowired
    private PrescriptionRepository presrepo;

    @Autowired
    private CustomerRepo custrepo;

    @Autowired
    private OrderRepo orderrepo;

    @GetMapping("/dashboardu/{emailId}")
    public String getEmail(@PathVariable String emailId,Model model){
        // System.out.println(emailId);
        email=emailId;
        List<Prescriptions> userPrescriptions= presrepo.getByEmail(emailId);
        model.addAttribute("userPrescriptions", userPrescriptions);
        return "presc";
    }

    @GetMapping("/addPrescription")
    public String addPres(Model model){
        model.addAttribute("custEmail",email);
        return "addPrescription";
    }

    @PostMapping("/savePrescription")
    public String addPrescription(@ModelAttribute Prescriptions prescriptions) {
        // Get the current user's ID from the User object (assuming you have a User object)
        int currentUserId = custrepo.getByEmail(email);

        // Set the customer_id in the prescription object
        prescriptions.setcustomer_id(currentUserId);

        // Save the new prescription to the database using the PrescriptionRepository
        presrepo.save(prescriptions);

        // Redirect to the user's dashboard or any other appropriate page
        return "redirect:/dashboardu/" + email;
    }
    
    @GetMapping("/profile")
    public String showprofile(Model model){
        try {
            Customer ourUser = custrepo.getDetails(email);
            customer_id=custrepo.findid(email);
            List<orders> myorder = orderrepo.getorders(customer_id);
            if (ourUser != null) {
                model.addAttribute("ourUser", ourUser);
                model.addAttribute("myorder", myorder);
                model.addAttribute("custEmail",email);
                return "profile";
            } else {
                // Handle the case when no user is found with the given email
                return "no_user_found"; // You can create a specific error page for this
            }
        } catch (EmptyResultDataAccessException e) {
            // Handle the case when no user is found with the given email
            return "no_user_found"; // You can create a specific error page for this
        }
    }

    
}
