package com.cj.library.dto.response;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer publicationYear;
    private String category;
    private Integer totalCopies;
    private Integer availableCopies;
}
