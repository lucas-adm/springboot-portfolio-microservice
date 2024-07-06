package com.adm.lucas.review.controller;

import com.adm.lucas.review.model.Comment;
import com.adm.lucas.review.model.dto.in.CreateCommentDTO;
import com.adm.lucas.review.model.dto.out.CommentDetailDTO;
import com.adm.lucas.review.model.dto.out.CommentNumbersDTO;
import com.adm.lucas.review.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    public ResponseEntity<Comment> save(@Valid @RequestBody CreateCommentDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Comment comment = service.save(dto.toComment());
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(comment.getId()).toUri();
        return ResponseEntity.created(uri).body(comment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDetailDTO> getComment(@PathVariable UUID id) {
        Comment comment = service.getComment(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(new CommentDetailDTO(comment));
    }

    @GetMapping
    public ResponseEntity<List<CommentDetailDTO>> getComments(@PageableDefault(size = 10, sort = {"stars"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Comment> page = service.getPage(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page.map(CommentDetailDTO::new).getContent());
    }

    @GetMapping("/numbers")
    public ResponseEntity<CommentNumbersDTO> getNumbers() {
        return ResponseEntity.status(HttpStatus.OK).body(new CommentNumbersDTO(service.getTotal(), service.getAvg()));
    }

}