package com.devtiro.jpaTest;

import com.devtiro.jpaTest.domain.Author;
import com.devtiro.jpaTest.domain.Book;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TestDataUtil {

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Hans Landa")
                .age(59).build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Roy Batty")
                .age(45).build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Anthon Churgh")
                .age(38).build();
    }


    // ------------------------------------------------------------------------ //

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("589-244-175")
                .title("Karamazov Brothers")
                .author(author).build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("100-32-049")
                .title("Incognito")
                .author(author).build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("236-935-918")
                .title("Slow and Rapid Thinking")
                .author(author).build();
    }
}
