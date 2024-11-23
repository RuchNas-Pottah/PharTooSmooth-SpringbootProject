package com.pharmacy.pharmacymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.pharmacymanagementsystem.doa.CustomerRepo;
import com.pharmacy.pharmacymanagementsystem.doa.UserRepo;
import com.pharmacy.pharmacymanagementsystem.models.Customer;
import com.pharmacy.pharmacymanagementsystem.models.User;

@Service
public class RegistrationService {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public void register (User users, Customer customer)
    {
        customer.setCustomer_email(users.getUserEmail());
        users.setRole("customer");
        userRepo.insertUser(users);
        customerRepo.insertCustomer(customer);
    }

}
