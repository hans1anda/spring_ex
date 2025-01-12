package com.devtiro.database;

import com.devtiro.database.domain.Author;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TestDataUtil {

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Hans Landa")
                .age(80).build();
    }
}
