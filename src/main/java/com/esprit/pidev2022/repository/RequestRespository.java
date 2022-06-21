package com.esprit.pidev2022.repository;

import com.esprit.pidev2022.entities.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface RequestRespository extends CrudRepository<Request,Long>
{


}
