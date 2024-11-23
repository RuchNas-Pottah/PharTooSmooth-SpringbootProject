package com.pharmacy.pharmacymanagementsystem.doa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pharmacy.pharmacymanagementsystem.models.Customer;
import com.pharmacy.pharmacymanagementsystem.models.Prescriptions;
import com.pharmacy.pharmacymanagementsystem.models.orders;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderRepo {
    @Autowired
    private JdbcTemplate t;

    public List<orders> getorders(int customerID) {
        return t.query("Select * from orders where customer_id=?", new BeanPropertyRowMapper <orders>(orders.class), customerID);
    }

    public void save(orders order) {

        t.update("insert into orders(customer_id, order_date, prescription_id, total_amount, address) values(?,?,?,?,?)",
                order.getCustomer_id(), order.getOrder_date() , order.getPrescription_id(), order.getTotal_amount(), order.getAddress());
    }
}
