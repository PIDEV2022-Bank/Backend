package com.esprit.pidev2022.repository;


import com.esprit.pidev2022.entities.Forum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ForumRepository extends CrudRepository<Forum, Long> {

    void deleteForumById(Long id);

   Optional<Forum> findForumById(Long id);
}
