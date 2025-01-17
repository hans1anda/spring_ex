package com.devtiro.jpaTest.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

    @Id
    private String isbn;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL) // This annotation tell us that you may create many books but you have one Author
    @JoinColumn(name = "author_id")
    private Author author;

}
