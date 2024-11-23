package com.pharmacy.pharmacymanagementsystem.doa;
import java.util.List;
import com.pharmacy.pharmacymanagementsystem.models.Prescriptions;

public interface PrescriptionRepository {
    int save(Prescriptions prescription);
    List<Prescriptions> getByEmail(String userEmail);
    int findCustId(int prescriptionId);
    List<Prescriptions> findAll();
    void setStatus(int prescid);

}
