package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.entities.Forum;
import com.esprit.pidev2022.entities.MyConstants;
import com.esprit.pidev2022.entities.Request;
import com.esprit.pidev2022.entities.User;
import com.esprit.pidev2022.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@Transactional
@RequestMapping("/Request")
public class RequestController {
    @Autowired
    public JavaMailSender emailSender;
    private final RequestService requestServ;


    public RequestController(RequestService requestServ) {
        this.requestServ = requestServ;
    }

    @PostMapping("/add")
    public ResponseEntity<Request> addRequest(@RequestBody Request request) {

        Request newReq=requestServ.addRequest(request);
       SimpleMailMessage message = new SimpleMailMessage();

       message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.emailSender.send(message);

        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Request>> getAllRequest() {

        List<Request> requests = requestServ.findAllRequest();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/requestsByUser/{id}")
    public ResponseEntity<List<Request>> getResquestByUser(@PathVariable("id") int id){
        User u=new User();
        u.setIdUser(id);
        List<Request> request=requestServ.findAllRequestByUser(u);
        return new ResponseEntity<>(request,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Request> updateRequest(@RequestBody Request request) {
        Request updateReq= requestServ.updateRequest(request);

        return new ResponseEntity<>(request, HttpStatus.OK);
    }


    @DeleteMapping("/remove/{Id}")
    public void removeRequest(@PathVariable("Id") Long id ){
requestServ.deleteRequest(id);

    }

}
