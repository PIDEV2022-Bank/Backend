package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.entities.Forum;
import com.esprit.pidev2022.entities.Request;
import com.esprit.pidev2022.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
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
}
