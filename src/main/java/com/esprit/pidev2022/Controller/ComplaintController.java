package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.entities.Complaint;
import com.esprit.pidev2022.entities.Forum;
import com.esprit.pidev2022.entities.Request;
import com.esprit.pidev2022.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/complaint")
public class ComplaintController {

    private  final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    /* @GetMapping("/all")
       * public ResponseEntity<List<Complaint>> getAllComplaints(){
             List<Complaint> complaints = complaintService.findAllComplaints();
             return new ResponseEntity<>(complaints, HttpStatus.OK);

         }*/
    @GetMapping("/all")
    public List<Complaint>getAllComplaints(){
        return  complaintService.findAllComplaints();}
    @GetMapping("/{complaintId}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable("complaintId") int complaintId){
        Complaint complaint=complaintService.findComplaintById(complaintId);
        return new ResponseEntity<>(complaint , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{complaintID}")
    public ResponseEntity<?> deleteComplaint(@PathVariable("complaintID") int complaintID) {
    Complaint complaint= complaintService.findComplaintById(complaintID);
        complaintService.deleteComplaint(complaint);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateStatusToDone/{complaintID}")
    public ResponseEntity<Complaint> updateComplaintStatus(@RequestBody int complaintID) {
        Complaint complaint= complaintService.findComplaintById(complaintID);
        complaintService.updateComplaintStatusToDone(complaint);
        return new ResponseEntity<>(complaint, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
         complaintService.addComplaint(complaint);
        return new ResponseEntity<>(complaint, HttpStatus.CREATED);
    }
}
