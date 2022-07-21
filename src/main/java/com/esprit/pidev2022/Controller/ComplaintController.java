package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.entities.Complaint;
import com.esprit.pidev2022.entities.MyConstants;
import com.esprit.pidev2022.security.model.User;
import com.esprit.pidev2022.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/complaint")
@CrossOrigin(origins = "http://localhost:4200")
public class ComplaintController {
    @Autowired
    public JavaMailSender emailSender;
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
    public ResponseEntity<Complaint> getComplaintById(@PathVariable("complaintId") Long complaintId){
        Complaint complaint=complaintService.findComplaintById(complaintId);
        return new ResponseEntity<>(complaint , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{complaintID}")
    public ResponseEntity<?> deleteComplaint(@PathVariable("complaintID") Long complaintID) {
    Complaint complaint= complaintService.findComplaintById(complaintID);
        complaintService.deleteComplaint(complaint);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateStatusToDone/{complaintID}")
    public ResponseEntity<Complaint> updateComplaintStatus(@PathVariable Long complaintID) {
        Complaint complaint= complaintService.findComplaintById(complaintID);
        complaintService.updateComplaintStatusToDone(complaint);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(complaint.getUser().getEmail());
        message.setSubject("your Bank");
        message.setText("Hi Mr. "+complaint.getUser().getUsername().toUpperCase() +", \n  you're complaint number "+complaint.getIdComplaint()+" has been treated ");

        this.emailSender.send(message);

        return new ResponseEntity<>(complaint, HttpStatus.OK);
    }
    @PutMapping("/updateStatusToRollback/{complaintID}")
    public ResponseEntity<Complaint> updateComplaintStatusToRollback(@PathVariable Long complaintID) {
        Complaint complaint= complaintService.findComplaintById(complaintID);
        complaintService.updateComplaintStatusToRollback(complaint);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(complaint.getUser().getEmail());
        message.setSubject("your Bank");
        message.setText("Hi Mr. "+complaint.getUser().getUsername().toUpperCase() +", \nSorry it was an error , you're complaint number "+complaint.getIdComplaint()+" will be treated as soon as possible ");
        this.emailSender.send(message);

        return new ResponseEntity<>(complaint, HttpStatus.OK);
    }
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
        User user = new User();
        user.setId(1);
        user.setUsername("FARES");
        complaint.setUser(user);
         complaintService.addComplaint(complaint);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("your Bank");
        message.setText("Hello, your complaint Status has changed to treated ");
        this.emailSender.send(message);

        return new ResponseEntity<>(complaint, HttpStatus.CREATED);
    }
}
