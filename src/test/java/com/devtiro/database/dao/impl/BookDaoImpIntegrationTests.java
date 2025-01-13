package com.devtiro.database.dao.impl;


import com.devtiro.database.TestDataUtil;
import com.devtiro.database.dao.IAuthorDao;
import com.devtiro.database.dao.IBookDao;
import com.devtiro.database.domain.Author;
import com.devtiro.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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

        assertThat(bookList).hasSize(3).containsExactly(bookA, bookB, bookC);

    }

}
