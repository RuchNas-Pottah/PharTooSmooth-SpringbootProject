package com.pharmacy.pharmacymanagementsystem.doa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pharmacy.pharmacymanagementsystem.models.medicationStock;

@Repository
public class MedImpl implements MedRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<medicationStock> findAll() {
        return jdbcTemplate.query("SELECT * FROM medication_stock", new BeanPropertyRowMapper<>(medicationStock.class));
    }

    @Override
    public int findPriceById(int medicineId){
        return jdbcTemplate.queryForObject("SELECT price FROM medication_stock WHERE id=?",Integer.class,medicineId);
    }

    @Override
    public void insertMedicationStock (medicationStock m)
    {
        String sql="INSERT INTO medication_stock(name,stock_quantity, price,dosage_strength,category_id) VALUES (?,?,?,?,?)";

        jdbcTemplate.update(sql,m.getName(),m.getStock_quantity(),m.getPrice(),m.getDosage_strength(),m.getCategory_id());
    }

    @Override
    public void updateMedicationStock(medicationStock m)
    {
        String sql="UPDATE medication_stock SET name=?,stock_quantity = ?, price = ?, dosage_strength = ?, category_id =? WHERE id = ?";

        jdbcTemplate.update(sql,m.getName(),m.getStock_quantity(),m.getPrice(),m.getDosage_strength(),m.getCategory_id(),m.getId());
    }

    @Override
    public void deleteMedicationStock(int i)
    {
        String sql="DELETE FROM medication_stock WHERE id=?";

        jdbcTemplate.update(sql,i);
    }
}
