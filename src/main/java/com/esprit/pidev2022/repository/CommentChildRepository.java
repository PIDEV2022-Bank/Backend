package com.esprit.pidev2022.repository;



import com.esprit.pidev2022.entities.CommentChild;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository

public interface CommentChildRepository extends CrudRepository<CommentChild, Long> {
    void deleteCommentChildById(Long id);

    Optional<CommentChild> findCommentChildById(Long id);
}
