package com.cj.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    @NotBlank(message = "isbn is required")
    private String isbn;

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "author is required")
    private String author;

    @NotBlank(message = "publisher is required")
    private String publisher;

    @NotNull(message = "publicationYear is required")
    private Integer publicationYear;

    @NotBlank(message = "category is required")
    private String category;

    @NotNull(message = "totalCopies is required")
    private Integer totalCopies;

    private Integer availableCopies;
}
