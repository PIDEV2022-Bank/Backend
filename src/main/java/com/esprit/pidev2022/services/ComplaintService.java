package com.esprit.pidev2022.services;


import com.esprit.pidev2022.entities.Complaint;
import com.esprit.pidev2022.security.model.User;
import com.esprit.pidev2022.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;

    public void addComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
    }

    public List<Complaint> findAllComplaints() {
        return (List<Complaint>) complaintRepository.findAll();
    }

    public List<Complaint> findComplaintByUser(User user) {
        return (List<Complaint>) complaintRepository.findById(Long.valueOf(user.getId())).get();
    }
    public Complaint findComplaintById(int ComplaintId){
        return  complaintRepository.findById(Long.valueOf(ComplaintId)).get();
    }
    public void deleteComplaint(Complaint complaint){
        complaintRepository.delete(complaint);
    }
    public Complaint updateComplaintClient(Complaint complaint){
        return complaintRepository.save(complaint);
    }
    public Complaint updateComplaintStatusBanker(Complaint complaint){
        return complaintRepository.save(complaint);
    }

    public void updateComplaintStatusToDone(Complaint complaint) {
        complaint.setStatus("done");
         complaintRepository.save(complaint);
    }
}
