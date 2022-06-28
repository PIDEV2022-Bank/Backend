package com.esprit.pidev2022.repository;

import com.esprit.pidev2022.entities.Complaint;
import org.springframework.data.repository.CrudRepository;

public interface ComplaintRepository extends CrudRepository<Complaint, Long> {
}
