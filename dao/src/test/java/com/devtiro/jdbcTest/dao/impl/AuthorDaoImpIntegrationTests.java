package com.devtiro.jdbcTest.dao.impl;

import com.devtiro.jdbcTest.TestDataUtil;
import com.devtiro.jdbcTest.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImpIntegrationTests {

    private final AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImpIntegrationTests(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {

        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);

        Optional<Author> result = underTest.findOne(author.getId());

        // Verifies that the Optional is not empty
        assertThat(result).isPresent();

        // Checks the content
        assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();

        underTest.create(authorA);
        underTest.create(authorB);
        underTest.create(authorC);

        List<Author> authorList = underTest.find();

        assertThat(authorList).hasSize(3).containsExactly(authorA, authorB, authorC);


    }

    @Test
    public void testThatAuthorCanBeUpdated() {

        Author authorB = TestDataUtil.createTestAuthorB();

        underTest.create(authorB);

        authorB.setName("Updated");

        underTest.update(authorB.getId(),authorB);

        Optional<Author> getOneAuthor = underTest.findOne(authorB.getId());

        assertThat(getOneAuthor).isPresent();

        assertThat(getOneAuthor.get()).isEqualTo(authorB);

    }

    @Test
    public void testThatAuthorCanBeDeletede() {

        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);

        underTest.delete(author.getId());

        Optional<Author> getOneAuthor = underTest.findOne(author.getId());

        assertThat(getOneAuthor).isEmpty();

    }

}
