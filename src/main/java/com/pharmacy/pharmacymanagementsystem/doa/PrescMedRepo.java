package com.pharmacy.pharmacymanagementsystem.doa;

import java.util.List;

import com.pharmacy.pharmacymanagementsystem.models.Prescriptions;
import com.pharmacy.pharmacymanagementsystem.models.prescribed_medicines;

public interface PrescMedRepo {
    List<prescribed_medicines> findByPrescriptionId(int prescriptionId);
    int save(prescribed_medicines prescribed_medicines);
}
