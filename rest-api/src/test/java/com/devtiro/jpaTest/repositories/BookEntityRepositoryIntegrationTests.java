package com.devtiro.jpaTest.repositories;


import com.devtiro.jpaTest.TestDataUtil;
import com.devtiro.jpaTest.domain.entities.AuthorEntity;
import com.devtiro.jpaTest.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityRepositoryIntegrationTests {

    private final IBookRepository underTest;

    @Autowired
    public BookEntityRepositoryIntegrationTests(IBookRepository underTest) {
        this.underTest = underTest;
    }


    @Test
    public void testThatBookCanBeCreatedAndRecalled() {

        AuthorEntity author = TestDataUtil.createTestAuthorA();

        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(author);

        underTest.save(bookEntity);

        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());

        assertThat(result).isPresent();

        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {

        AuthorEntity author = TestDataUtil.createTestAuthorA();

        BookEntity bookEntityA = TestDataUtil.createTestBookEntityA(author);
        BookEntity bookEntityB = TestDataUtil.createTestBookB(author);
        BookEntity bookEntityC = TestDataUtil.createTestBookC(author);

        underTest.save(bookEntityA);
        underTest.save(bookEntityB);
        underTest.save(bookEntityC);


        Iterable<BookEntity> bookList = underTest.findAll();

        // Verifies that the Optional is not empty and checks context
        assertThat(bookList).hasSize(3).containsExactly(bookEntityA, bookEntityB, bookEntityC);

    }

    @Test
    public void testThatBookCanBeUpdated() {

        AuthorEntity author = TestDataUtil.createTestAuthorA();

        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(author);

        underTest.save(bookEntity);

        bookEntity.setTitle("Updated");

        underTest.save(bookEntity);

        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());

        assertThat(result).isPresent();

        assertThat(result.get()).isEqualTo(bookEntity);

    }

    @Test
    public void testThatBookCanBeDeleted() {

        AuthorEntity author = TestDataUtil.createTestAuthorA();

        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(author);

        underTest.deleteById(bookEntity.getIsbn());

        Optional<BookEntity> getOneBook = underTest.findById(bookEntity.getIsbn());

        assertThat(getOneBook).isEmpty();

    }

}
