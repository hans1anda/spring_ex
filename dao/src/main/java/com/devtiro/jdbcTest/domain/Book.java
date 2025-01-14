package com.devtiro.jdbcTest.domain;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private String isbn;

    private String title;

    private Long authorId;

}
