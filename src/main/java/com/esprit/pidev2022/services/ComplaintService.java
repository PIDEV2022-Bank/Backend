package com.esprit.pidev2022.services;


import com.esprit.pidev2022.Dto.ComplaintUserDTO;
import com.esprit.pidev2022.entities.Complaint;
import com.esprit.pidev2022.security.model.User;
import com.esprit.pidev2022.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;

    public List<ComplaintUserDTO> findAllComplaintsUsers(){
        List<ComplaintUserDTO> list = StreamSupport.stream(complaintRepository.findAll().spliterator(),false).map(this::convertEntityToDTO).collect(Collectors.toList());
  return list;
    }
    public ComplaintUserDTO convertEntityToDTO(Complaint complaint){
        ComplaintUserDTO cuDTO = new ComplaintUserDTO();
cuDTO.setIdComplaint(complaint.getIdComplaint());
cuDTO.setSubject(complaint.getSubject());
cuDTO.setMessage(complaint.getMessage());
cuDTO.setStatus(complaint.getStatus());
cuDTO.setIdUser(complaint.getUser().getId());
cuDTO.setUsername(complaint.getUser().getUsername());

        return cuDTO;
    }
    public void addComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
    }

    public List<Complaint> findAllComplaints() {
        return (List<Complaint>) complaintRepository.findAll();
    }

//    public List<Complaint> findComplaintByUser(User user) {
//        return (List<Complaint>) complaintRepository.findById(Long.valueOf(user.getIdUser())).get();
//    }
public ComplaintUserDTO findComplaintByIdDTO(Long ComplaintId){

    return complaintRepository.findById(ComplaintId).map(this::convertEntityToDTO).get();


}
    public Complaint findComplaintById(Long ComplaintId){
        return  complaintRepository.findById(ComplaintId).get();
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
    }   public void updateComplaintStatusToRollback(Complaint complaint) {
        complaint.setStatus("not treated yet");
         complaintRepository.save(complaint);
    }
}
