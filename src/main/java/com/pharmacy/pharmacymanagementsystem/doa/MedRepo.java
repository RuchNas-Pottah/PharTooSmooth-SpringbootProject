package com.pharmacy.pharmacymanagementsystem.doa;

import java.util.List;

import com.pharmacy.pharmacymanagementsystem.models.medicationStock;

public interface MedRepo {
    List<medicationStock> findAll();
    int findPriceById(int medicineId);
    public void deleteMedicationStock(int i);
    public void updateMedicationStock(medicationStock m);
    public void insertMedicationStock (medicationStock m);
}
