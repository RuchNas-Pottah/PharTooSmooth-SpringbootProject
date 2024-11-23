package com.pharmacy.pharmacymanagementsystem.doa;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pharmacy.pharmacymanagementsystem.models.*;

@Repository
public class QueriesRepo {
    
    @Autowired
    private JdbcTemplate t;

    public User selectUser(String u) {

        try {

            String x = "SELECT * FROM Users WHERE userEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(User.class),u);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void addNewMedicine(medicationStock m, categories c)
    {
        String x="INSERT INTO medication_stock(name,stock_quantity, price,dosage_strength,category_id) VALUES (?,?,?,?,?)";

        t.update(x,m.getName(),m.getStock_quantity(),m.getPrice(),m.getDosage_strength(),c.getCat_id());

    }
    public void restockMedicine(medicationStock m, IncreaseMedicationStock q)
    {
        String sql="UPDATE medication_stock SET stock_quantity=?+? WHERE id=?";

        t.update(sql, m.getStock_quantity(),q.getQuantity(),m.getId());

    }



     public List<medicationStock> getMedicinesByCategoryId(int i) {
        try {

            String x = "SELECT * FROM medication_stock  WHERE category_id=?";

            return t.query(x, new BeanPropertyRowMapper<>(medicationStock.class),i);

        }

        catch (EmptyResultDataAccessException e) {
            System.out.println("ono");

            return null;

        }

    }
    public List<categories> getCategories() {
        try {

            String x = "SELECT * FROM categories";

            return t.query(x, new BeanPropertyRowMapper<>(categories.class));

        }

        catch (EmptyResultDataAccessException e) {
            System.out.println("ono");

            return null;

        }

    }
    public List<orders> getOrders() {
        try {

            String x = "SELECT * FROM orders";

            return t.query(x, new BeanPropertyRowMapper<>(orders.class));

        }

        catch (EmptyResultDataAccessException e) {
            System.out.println("ono");

            return null;

        }

    }

    public medicationStock getMedicineById(int v) {

        try {

            String x = "SELECT * FROM medication_stock WHERE id = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(medicationStock.class), new Object[] { v });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }
    public categories getCategoryById(int v) {

        try {

            String x = "SELECT * FROM categories WHERE cat_id = ?";
            

            return t.queryForObject(x, new BeanPropertyRowMapper<>(categories.class), new Object[] { v });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }
    public Customer getCustomerById(int v) {

        try {

            String x = "SELECT * FROM customers WHERE id = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Customer.class), new Object[] { v });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }
    public Customer getCustomerByEmail(String email) {

        try {

            String x = "SELECT * FROM customers WHERE customer_email = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Customer.class), new Object[] { email });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }
    public prescribed_medicines getPrescribedMedicine(int medication_id, int prescId) {

        try {

            String x = "SELECT * FROM prescribed_medicines WHERE prescription_id=? and medication_id=? ";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(prescribed_medicines.class), new Object[] { prescId,medication_id });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }
    public void AddMedicineToPrescription(int medication_id, int prescId, IncreaseMedicineQuantity q,prescribed_medicines p)
    {
       
             String x = "update prescribed_medicines set quantity=?+? where prescription_id=? and medication_id=?";
             t.update(x,p.getQuantity(),q.getQuantity(),p.getPrescription_id(),p.getMedication_id());
   
    }

}