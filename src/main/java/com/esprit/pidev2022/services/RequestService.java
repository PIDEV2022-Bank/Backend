package com.esprit.pidev2022.services;


import com.esprit.pidev2022.entities.Request;
import com.esprit.pidev2022.security.model.User;
import com.esprit.pidev2022.repository.RequestRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RequestService {
    @Autowired RequestRespository requestRepo;

    public Request addRequest(Request req) {
        return requestRepo.save(req);
    }
    public List<Request> findAllRequest()
    {

        return (List<Request>) requestRepo.findAll();
    }

    public List<Request> findAllRequestByUser(User u){
        return (List<Request>) requestRepo.findById(Long.valueOf(u.getId())).get();
    }

}
