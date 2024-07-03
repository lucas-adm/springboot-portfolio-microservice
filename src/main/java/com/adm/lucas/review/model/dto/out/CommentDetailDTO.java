package com.adm.lucas.review.model.dto.out;

import com.adm.lucas.review.model.Comment;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public record CommentDetailDTO(
        String name,
        String socialMedia,
        String photo,
        String dateTime,
        int stars,
        String content
) {
    public CommentDetailDTO(Comment comment) {
        this(
                comment.getName(),
                comment.getSocialMedia(),
                comment.getPhoto(),
                comment.getDateTime().format(DateTimeFormatter.ofPattern("d MMMM, yyyy HH:mm", new Locale("pt", "BR"))),
                comment.getStars(),
                comment.getContent()
        );
    }
}