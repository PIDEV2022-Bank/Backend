package com.esprit.pidev2022.repository;

import com.esprit.pidev2022.entities.Comment;
import com.esprit.pidev2022.entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    void deletePostById(Long id);
 List<Post> findAllByForumId(Long id);
    Optional<Post> findPostById(Long id);
}
