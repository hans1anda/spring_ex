package com.devtiro.jpaTest.repositories;

import com.devtiro.jpaTest.TestDataUtil;
import com.devtiro.jpaTest.domain.entities.AuthorEntity;
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
public class AuthorRepositoryIntegrationTests {

    private final AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {

        AuthorEntity author = TestDataUtil.createTestAuthorA();

        underTest.save(author);

        Optional<AuthorEntity> result = underTest.findById(author.getId());

        // Verifies that the Optional is not empty
        assertThat(result).isPresent();

        // Checks the content
        assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
        AuthorEntity authorC = TestDataUtil.createTestAuthorC();

        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        Iterable<AuthorEntity> authorList = underTest.findAll();

        assertThat(authorList).hasSize(3).containsExactly(authorA, authorB, authorC);

    }

    @Test
    public void testThatAuthorCanBeUpdated() {

        AuthorEntity authorB = TestDataUtil.createTestAuthorB();

        underTest.save(authorB);

        authorB.setName("Updated");

        underTest.save(authorB);

        Optional<AuthorEntity> result = underTest.findById(authorB.getId());

        assertThat(result).isPresent();

        assertThat(result.get()).isEqualTo(authorB);

    }

    @Test
    public void testThatAuthorCanBeDeletede() {

        AuthorEntity author = TestDataUtil.createTestAuthorA();

        underTest.save(author);

        underTest.deleteById(author.getId());

        Optional<AuthorEntity> getOneAuthor = underTest.findById(author.getId());

        assertThat(getOneAuthor).isEmpty();

    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        underTest.save(testAuthorA);
        AuthorEntity testAuthorB = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorB);
        AuthorEntity testAuthorC = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorC);

        Iterable<AuthorEntity> authorswhichlessThanFifteenYearsOld = underTest.ageLessThan(50);

        assertThat(authorswhichlessThanFifteenYearsOld).containsExactly(testAuthorB, testAuthorC);


    }

    @Test
    public void testThatGetAuthorsWithGreaterThan() {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        underTest.save(testAuthorA);
        AuthorEntity testAuthorB = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorB);
        AuthorEntity testAuthorC = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorC);

        Iterable<AuthorEntity> authorswhichlessThanFifteenYearsOld = underTest.findAuthorsWithAgeGreaterThan(50);

        assertThat(authorswhichlessThanFifteenYearsOld).containsExactly(testAuthorA);


    }

}
