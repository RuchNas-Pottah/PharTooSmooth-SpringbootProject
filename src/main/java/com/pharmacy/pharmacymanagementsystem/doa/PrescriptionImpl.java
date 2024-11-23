package com.pharmacy.pharmacymanagementsystem.doa;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.pharmacy.pharmacymanagementsystem.models.Prescriptions;
import com.pharmacy.pharmacymanagementsystem.models.Customer;

@Repository
public class PrescriptionImpl implements PrescriptionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int save(Prescriptions prescriptions) {
        return jdbcTemplate.update("insert into prescriptions(customer_id, doctor_id, date_of_prescription, status)values(?,?,?,false)",prescriptions.getcustomer_id(), prescriptions.getdoctor_id(), prescriptions.getdate_of_prescription());
    }
    
    @Override
    public List<Prescriptions> getByEmail(String userEmail) {
        return jdbcTemplate.query("Select prescriptions.id, prescriptions.doctor_id, prescriptions.date_of_prescription, prescriptions.status from prescriptions, customers where prescriptions.customer_id=customers.id and customers.customer_email=?", new BeanPropertyRowMapper <Prescriptions>(Prescriptions.class), userEmail);
    }

    @Override
    public int findCustId(int prescriptionId){
        try {
            return jdbcTemplate.queryForObject("Select customer_id from prescriptions where id=?", Integer.class, prescriptionId);
        } catch (EmptyResultDataAccessException ex) {
            // Handle the case where no records were found
            return -1; // Or any appropriate default value or error handling
        }
    }

    @Override
    public List<Prescriptions> findAll(){
        return jdbcTemplate.query("Select * from prescriptions",new BeanPropertyRowMapper <Prescriptions>(Prescriptions.class));
    }

    @Override
    public void setStatus(int prescid){
        jdbcTemplate.update("Update Prescriptions set status=1 where id=?", prescid);
    }
    
    
}
