package com.pharmacy.pharmacymanagementsystem.doa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pharmacy.pharmacymanagementsystem.models.Customer;

@Repository
public class CustomerRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertCustomer(Customer c)
    {
        String x="INSERT INTO customers(customer_email,f_name,l_name,phone_no,dob,address) VALUES (?,?,?,?,?,?)";
        t.update(x,c.getCustomer_email(), c.getF_name(),c.getL_name(),c.getPhone_no(),c.getDob(),c.getAddress());
    }

    public void updateCustomer (Customer c)
    {
        String x=" UPDATE customers SET customer_email=? ,f_name = ?, l_name = ?, phone_no = ?,  dob = ?, address = ? WHERE id = ? ";
        t.update(
            x,
            c.getCustomer_email(),
            c.getF_name(),
            c.getL_name(),
            c.getPhone_no(),
            c.getDob(),
            c.getAddress(),
            c.getId()
        );
    }

    public void deleteCustomer(int i)
    {
        String sql = "DELETE FROM customers WHERE id = ?";

        t.update(sql, i);

    }

    public int getByEmail(String emailID) {
        try {
            System.out.println(emailID);
            return t.queryForObject("SELECT id FROM customers WHERE customer_email = ?", Integer.class, emailID);
        } catch (EmptyResultDataAccessException ex) {
            // Handle the case where no records were found
            return -1; // Or any appropriate default value or error handling
        }
    }

    public Customer getDetails(String emailID){
        return t.queryForObject("SELECT * FROM customers WHERE customer_email=?",new BeanPropertyRowMapper<Customer>(Customer.class),emailID);
    }

    public String findEmail(int custId){
        return t.queryForObject("Select customer_email from customers WHERE id=?",String.class,custId);
    }

    public int findid(String emailID){
        return t.queryForObject("Select id from customers WHERE customer_email=?",Integer.class,emailID);
    }

    public String findAddress(int custId){
        return t.queryForObject("Select address from customers WHERE id=?",String.class,custId);
    }

}
