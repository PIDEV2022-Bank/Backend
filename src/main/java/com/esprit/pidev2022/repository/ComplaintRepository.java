package com.esprit.pidev2022.repository;

import com.esprit.pidev2022.entities.Complaint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, Long> {
}
