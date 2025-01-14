package com.devtiro.database;

import com.devtiro.database.domain.Author;
import com.devtiro.database.domain.Book;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TestDataUtil {

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Hans Landa")
                .age(49).build();
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

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("589-244-175")
                .title("Karamazov Brothers")
                .authorId(1L).build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("100-32-049")
                .title("Incognito")
                .authorId(2L).build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("236-935-918")
                .title("Slow and Rapid Thinking")
                .authorId(3L).build();
    }
}
