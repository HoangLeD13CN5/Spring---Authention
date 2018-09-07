package com.example.poll.repository;

import com.example.poll.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findByCreatedBy(Long userId, Pageable pageable);

    Page<Post> findAll(Pageable pageable);
}
