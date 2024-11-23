package com.pharmacy.pharmacymanagementsystem.doa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pharmacy.pharmacymanagementsystem.models.prescribed_medicines;

@Repository
public class PrescMedImpl implements PrescMedRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<prescribed_medicines> findByPrescriptionId(int prescriptionId) {
        return jdbcTemplate.query(
            "SELECT * FROM prescribed_medicines WHERE prescription_id = ?",
            new BeanPropertyRowMapper<>(prescribed_medicines.class),
            prescriptionId
        );
    }

    @Override
    public int save(prescribed_medicines prescribed_medicines) {
        return jdbcTemplate.update("insert into prescribed_medicines(prescription_id,medication_id,quantity)values(?,?,?)",prescribed_medicines.getPrescription_id(), prescribed_medicines.getMedication_id(), prescribed_medicines.getQuantity());
    }

    
}
