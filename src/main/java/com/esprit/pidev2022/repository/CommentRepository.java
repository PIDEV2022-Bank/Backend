package com.esprit.pidev2022.repository;

import com.esprit.pidev2022.entities.Comment;
import com.esprit.pidev2022.entities.Forum;
import com.esprit.pidev2022.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    void deleteCommentById(Long id);

    Optional<Comment> findCommentById(Long id);
    List<Comment> findCommentByPostId(Long id);
}
