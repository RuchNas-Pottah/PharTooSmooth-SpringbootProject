package com.pharmacy.pharmacymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.pharmacymanagementsystem.doa.CategoryRepo;
import com.pharmacy.pharmacymanagementsystem.doa.MedRepo;
import com.pharmacy.pharmacymanagementsystem.doa.QueriesRepo;
import com.pharmacy.pharmacymanagementsystem.models.categories;
import com.pharmacy.pharmacymanagementsystem.models.Customer;
import com.pharmacy.pharmacymanagementsystem.models.IncreaseMedicationStock;
import com.pharmacy.pharmacymanagementsystem.models.medicationStock;
import com.pharmacy.pharmacymanagementsystem.models.orders;

@Service
public class AdminService {

    @Autowired
    private MedRepo medicationStockRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private QueriesRepo queriesRepo;
    public void addMedicationStock(medicationStock medicine, categories category)
    {
        queriesRepo.addNewMedicine(medicine,category);
    }
    public void addCategory(categories c)
    {
        categoryRepo.insertCategory(c);
    }
    public categories getCategoryById(int i)
    {
        return queriesRepo.getCategoryById(i);
    }
    public void DeleteCategory(int id) {
        
        categoryRepo.deleteCategory(id);
    }
    public void deleteMedicine(int id) {
        
        medicationStockRepo.deleteMedicationStock(id);
    }
    public List<medicationStock> getMedicinesByCategoryId(int i)
    {
        return queriesRepo.getMedicinesByCategoryId(i);
    }
    public medicationStock getMedicineById(int id)
    {
        return queriesRepo.getMedicineById(id);
    }
    public List<categories> getCategories()
    {
        return queriesRepo.getCategories();
    }
    public void restock(medicationStock m,IncreaseMedicationStock q)
    {
        queriesRepo.restockMedicine(m,q);
    }
    public List<orders> getOrders()
    {
        return queriesRepo.getOrders();
    }
    public Customer getCustomerById(int id)
    {
        return queriesRepo.getCustomerById(id);
    }
    public Customer getCustomerByEmail(String email)
    {
        return queriesRepo.getCustomerByEmail(email);
    }
    
    

}