# JDBC Template CRUD Operations

This README provides an overview of a project where CRUD (Create, Read, Update, Delete) operations are implemented using the `JdbcTemplate` class in Spring Framework. Below is an example implementation with test cases for an `Author` entity and a similar `Book` entity.

## Prerequisites

Before running this project, ensure you have the following:

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Spring Framework**: Spring Boot version 2.x or higher.
- **Database**: A relational database like MySQL, PostgreSQL, or H2.
- **Maven**: For managing project dependencies.

## Project Structure

The project follows a typical Spring Boot project structure:

```
├── src/main/java
│   ├── com.example.crud
│       ├── domain        // Contains entity classes (e.g., Author, Book)
│       ├── dao           // Contains DAO interfaces
│       ├── dao.impl      // Contains DAO implementations using JdbcTemplate
│       ├── service       // Contains service layer logic
│       ├── controller    // Contains REST API controllers
├── src/main/resources
│   ├── application.properties // Configuration file for database settings
```

## Dependencies

Ensure the following dependencies are added to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.assertj</groupId>
        <artifactId>assertj-core</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Configuration

Set up your database connection in the `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## CRUD Implementation with Tests

### Example Test Class: `AuthorDaoImplIntegrationTests`

Below is a sample test class demonstrating CRUD operations for an `Author` entity:

```java
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
        underTest.update(authorB.getId(), authorB);

        Optional<Author> getOneAuthor = underTest.findOne(authorB.getId());

        assertThat(getOneAuthor).isPresent();
        assertThat(getOneAuthor.get()).isEqualTo(authorB);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);

        underTest.delete(author.getId());

        Optional<Author> getOneAuthor = underTest.findOne(author.getId());
        assertThat(getOneAuthor).isEmpty();
    }
}
```

### Notes:
- Replace `Author` with `Book` for testing similar CRUD operations for the `Book` entity.
- Ensure `TestDataUtil` contains utility methods to create test data for `Author` and `Book` entities.

## Testing the Application

You can test the CRUD functionality via:

- **Postman**: Use HTTP requests to test endpoints.
- **JUnit**: Write unit tests as demonstrated above.

## Conclusion

This project demonstrates how to use `JdbcTemplate` for performing CRUD operations in a Spring Boot application. You can extend the same logic to additional entities like `Book` or others as per your project requirements.

