package com.devtiro.jpaTest.repositories;

import com.devtiro.jpaTest.TestDataUtil;
import com.devtiro.jpaTest.domain.Author;
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

        Author author = TestDataUtil.createTestAuthorA();

        underTest.save(author);

        Optional<Author> result = underTest.findById(author.getId());

        // Verifies that the Optional is not empty
        assertThat(result).isPresent();

        // Checks the content
        assertThat(result.get()).isEqualTo(author);

    }

//    @Test
//    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
//        Author authorA = TestDataUtil.createTestAuthorA();
//        Author authorB = TestDataUtil.createTestAuthorB();
//        Author authorC = TestDataUtil.createTestAuthorC();
//
//        underTest.create(authorA);
//        underTest.create(authorB);
//        underTest.create(authorC);
//
//        List<Author> authorList = underTest.find();
//
//        assertThat(authorList).hasSize(3).containsExactly(authorA, authorB, authorC);
//
//
//    }
//
//    @Test
//    public void testThatAuthorCanBeUpdated() {
//
//        Author authorB = TestDataUtil.createTestAuthorB();
//
//        underTest.create(authorB);
//
//        authorB.setName("Updated");
//
//        underTest.update(authorB.getId(),authorB);
//
//        Optional<Author> getOneAuthor = underTest.findOne(authorB.getId());
//
//        assertThat(getOneAuthor).isPresent();
//
//        assertThat(getOneAuthor.get()).isEqualTo(authorB);
//
//    }
//
//    @Test
//    public void testThatAuthorCanBeDeletede() {
//
//        Author author = TestDataUtil.createTestAuthorA();
//
//        underTest.create(author);
//
//        underTest.delete(author.getId());
//
//        Optional<Author> getOneAuthor = underTest.findOne(author.getId());
//
//        assertThat(getOneAuthor).isEmpty();
//
//    }

}
