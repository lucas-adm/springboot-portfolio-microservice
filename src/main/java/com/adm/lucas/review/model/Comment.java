package com.adm.lucas.review.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
public class Comment {

    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private String socialMedia;
    private String photo;
    private LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    private int stars;
    private String content;

    public Comment(String name, String socialMedia, String photo, int stars, String content) {
        this.name = name;
        this.socialMedia = socialMedia;
        this.photo = photo;
        this.stars = stars;
        this.content = content;
    }

}