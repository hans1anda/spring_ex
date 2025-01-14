package com.devtiro.jdbcTest.dao.impl;


import com.devtiro.jdbcTest.TestDataUtil;
import com.devtiro.jdbcTest.dao.IAuthorDao;
import com.devtiro.jdbcTest.domain.Author;
import com.devtiro.jdbcTest.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImpIntegrationTests {

    private final IAuthorDao authorDao;

    private final BookDaoImpl underTest;

    @Autowired
    public BookDaoImpIntegrationTests(IAuthorDao authorDao, BookDaoImpl underTest) {
        this.authorDao = authorDao;
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {

        Author author = TestDataUtil.createTestAuthorA();

        authorDao.create(author);

        Book book = TestDataUtil.createTestBookA();

        book.setAuthorId(author.getId());

        underTest.create(book);

        Optional<Book> results = underTest.findOne(book.getIsbn());

        assertThat(results).isPresent();

        assertThat(results.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {

        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book bookA = TestDataUtil.createTestBookA();
        Book bookB = TestDataUtil.createTestBookB();
        Book bookC = TestDataUtil.createTestBookC();

        bookA.setAuthorId(author.getId());
        bookB.setAuthorId(author.getId());
        bookC.setAuthorId(author.getId());

        underTest.create(bookA);
        underTest.create(bookB);
        underTest.create(bookC);


        List<Book> bookList = underTest.find();

        // Verifies that the Optional is not empty and checks context
        assertThat(bookList).hasSize(3).containsExactly(bookA, bookB, bookC);

    }

    @Test
    public void testThatBookCanBeUpdated() {

        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());

        book.setTitle("Updated");
        underTest.create(book);


        underTest.update(book.getIsbn(), book);

        Optional<Book> getOneBook = underTest.findOne(book.getIsbn());

        assertThat(getOneBook).isPresent();

        assertThat(getOneBook.get()).isEqualTo(book);

    }

    @Test
    public void testThatBookCanBeDeleted() {

        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());

        underTest.delete(book.getIsbn());

        Optional<Book> getOneBook = underTest.findOne(book.getIsbn());

        assertThat(getOneBook).isEmpty();

    }

}
