package com.esprit.pidev2022.services;


import com.esprit.pidev2022.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import javax.transaction.Transactional;

@Service
@Transactional
public class ComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;

}
