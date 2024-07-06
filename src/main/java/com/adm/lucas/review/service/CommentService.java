package com.adm.lucas.review.service;

import com.adm.lucas.review.model.Comment;
import com.adm.lucas.review.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    public Comment save(Comment comment) {
        if (!comment.getSocialMedia().startsWith("http")) {
            comment.setSocialMedia("https://" + comment.getSocialMedia());
        }
        comment.setSocialMedia(comment.getSocialMedia().replace("http://", "https://").toLowerCase());
        return repository.save(comment);
    }

    public Comment getComment(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<Comment> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int getTotal() {
        return repository.findAll().size();
    }

    public Double getAvg() {
        return repository.findAvg();
    }

}