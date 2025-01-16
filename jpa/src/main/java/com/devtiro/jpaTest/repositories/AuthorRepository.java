package com.devtiro.jpaTest.repositories;

import com.devtiro.jpaTest.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Iterable<Author> ageLessThan(int age);

    // This called Hibernate Query Language ( HQL )
    @Query("SELECT a FROM Author a WHERE a.age > :age")
    Iterable<Author> findAuthorsWithAgeGreaterThan(@Param("age") int age);

}
