package com.devtiro.jpaTest.repositories;

import com.devtiro.jpaTest.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
