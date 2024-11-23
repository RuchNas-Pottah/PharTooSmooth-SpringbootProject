package com.pharmacy.pharmacymanagementsystem.models;
import java.sql.Date;

import javax.sql.rowset.serial.SerialBlob;
public class Prescriptions {
    public int id;
    public int customer_id;
    public int doctor_id;
    public Date date_of_prescription;
    public Boolean status;

    public Integer getid(){
        return id;
    }

    public void setid(int id){
        this.id=id;
    }

    public Integer getcustomer_id(){
        return customer_id;
    }

    public void setcustomer_id(int customer_id){
        this.customer_id=customer_id;
    }

    public Integer getdoctor_id(){
        return doctor_id;
    }

    public void setdoctor_id(int doctor_id){
        this.doctor_id=doctor_id;
    }

    public Date getdate_of_prescription(){
        return date_of_prescription;
    }

    public void setdate_of_prescription(Date date_of_prescription){
        this.date_of_prescription=date_of_prescription;
    }

    public boolean getstatus(){
        return status;
    }

    public void setstatus(boolean status){
        this.status=status;
    }
}
