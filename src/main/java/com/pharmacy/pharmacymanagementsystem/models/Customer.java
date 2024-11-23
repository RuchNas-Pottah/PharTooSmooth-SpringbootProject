package com.pharmacy.pharmacymanagementsystem.models;

import java.sql.Date;



public class Customer {
    
    private int id;

    private String customer_email;

    private String f_name;

    private String l_name;

    private String phone_no;

    private Date dob;
    
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }
    
    
    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", customer_email=" + customer_email + ", f_name=" + f_name + ", l_name=" + l_name
                + ", phone_no=" + phone_no + ", dob=" + dob + ", address=" + address + "]";
    }

    




}
