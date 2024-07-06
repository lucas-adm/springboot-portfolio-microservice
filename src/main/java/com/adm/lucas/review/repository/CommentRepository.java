package com.adm.lucas.review.repository;

import com.adm.lucas.review.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Override
    @NonNull
    Page<Comment> findAll(@NonNull Pageable pageable);

    @Query("SELECT AVG(c.stars) FROM Comment c")
    Double findAvg();

}