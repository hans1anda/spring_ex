package com.devtiro.jpaTest;

import com.devtiro.jpaTest.domain.dto.AuthorDto;
import com.devtiro.jpaTest.domain.dto.BookDto;
import com.devtiro.jpaTest.domain.entities.AuthorEntity;
import com.devtiro.jpaTest.domain.entities.BookEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TestDataUtil {

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Hans Landa")
                .age(59).build();
    }

    public static AuthorDto createTestAuthorDto() {
        return AuthorDto.builder()
                .id(7L)
                .name("Kernel Truck")
                .age(999).build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Roy Batty")
                .age(45).build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Anthon Churgh")
                .age(38).build();
    }


    // ------------------------------------------------------------------------ //

    public static BookEntity createTestBookEntityA(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("589-244-175")
                .title("Karamazov Brothers")
                .author(author).build();
    }

    public static BookDto createTestBookDto(final AuthorDto author) {
        return BookDto.builder()
                .isbn("100-32-049")
                .title("Karamazov Brothers")
                .author(author).build();
    }

    public static BookEntity createTestBookB(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("100-32-049")
                .title("Incognito")
                .author(author).build();
    }

    public static BookEntity createTestBookC(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("236-935-918")
                .title("Slow and Rapid Thinking")
                .author(author).build();
    }
}
