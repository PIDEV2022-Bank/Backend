package com.esprit.pidev2022.services;


import com.esprit.pidev2022.entities.Request;
import com.esprit.pidev2022.security.model.User;
import com.esprit.pidev2022.repository.RequestRespository;
import com.esprit.pidev2022.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RequestService {
    @Autowired RequestRespository requestRepo;
    @Autowired UserRepository userRepo;

    public Request addRequest(Request req) {
        return requestRepo.save(req);
    }
    public List<Request> findAllRequest()
    {

        return (List<Request>) requestRepo.findAll();
    }

    public List<Request> findAllRequestByUser(User u){
     //   return (List<Request>) requestRepo.findById(Long.valueOf(u.getId())).get();
        return userRepo.findById(Long.valueOf(u.getId())).get().getRequests();
    }
public Request updateRequest(Request r){
        return  requestRepo.save(r);
}
    public void deleteRequest(Long id) {
        requestRepo.deleteById(id);

    }
    public void updateRequestStatustoDone(Request request){
        request.setState("Done");
        requestRepo.save(request);
    }
    public Request findById(Long id){
        return requestRepo.findById(id).get();
    }
}
