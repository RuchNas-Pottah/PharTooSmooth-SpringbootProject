package com.pharmacy.pharmacymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.pharmacymanagementsystem.doa.PrescMedRepo;
import com.pharmacy.pharmacymanagementsystem.doa.QueriesRepo;
import com.pharmacy.pharmacymanagementsystem.models.IncreaseMedicineQuantity;
import com.pharmacy.pharmacymanagementsystem.models.prescribed_medicines;

@Service
public class MedicineService {
    
    @Autowired
    private QueriesRepo queriesRepo;


    public void AddMedicineToPrescription(int medication_id, int prescId, IncreaseMedicineQuantity q,prescribed_medicines p)
    {
        queriesRepo.AddMedicineToPrescription(medication_id,  prescId,  q,p);
    }
    public prescribed_medicines getPrescribedMedicine(int medication_id, int prescId)
    {
        return queriesRepo.getPrescribedMedicine(medication_id, prescId);
    }


}
