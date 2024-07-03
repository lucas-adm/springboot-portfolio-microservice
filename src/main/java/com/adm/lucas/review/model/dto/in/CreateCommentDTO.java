package com.adm.lucas.review.model.dto.in;

import com.adm.lucas.review.model.Comment;
import jakarta.validation.constraints.*;

public record CreateCommentDTO(
        @NotBlank(message = "Não pode ser vazio")
        @Size(min = 3, max = 33, message = "Deve ter entre 3 e 33 caracteres")
        String name,

        @Size(max = 333, message = "Deve ter até 333 caracteres")
        String socialMedia,

        @NotBlank(message = "Não pode ser vazio")
        String photo,

        @NotNull(message = "Não pode ser nulo")
        @Min(value = 1)
        @Max(value = 5)
        int stars
) {
    public Comment toComment() {
        return new Comment(name, socialMedia, photo, stars);
    }
}