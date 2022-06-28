package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.entities.Forum;
import com.esprit.pidev2022.entities.Request;
import com.esprit.pidev2022.entities.User;
import com.esprit.pidev2022.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/Request")
public class RequestController {

    private final RequestService requestServ;


    public RequestController(RequestService requestServ) {
        this.requestServ = requestServ;
    }

    @PostMapping("/add")
    public ResponseEntity<Request> addRequest(@RequestBody Request request) {

        Request newReq=requestServ.addRequest(request);
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
}
