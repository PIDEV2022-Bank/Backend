package com.esprit.pidev2022.repository;

import com.esprit.pidev2022.entities.Forum;
import com.esprit.pidev2022.entities.Forum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ForumRepository extends CrudRepository<Forum, Long> {

    void deleteForumById(Long id);

   Optional<Forum> findForumById(Long id);
}
