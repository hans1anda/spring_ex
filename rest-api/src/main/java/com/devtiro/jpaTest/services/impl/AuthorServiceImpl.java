package com.devtiro.jpaTest.services.impl;

import com.devtiro.jpaTest.domain.entities.AuthorEntity;
import com.devtiro.jpaTest.repositories.IAuthorRepository;
import com.devtiro.jpaTest.services.IAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements IAuthorService {

    private final IAuthorRepository authorRepository;

    public AuthorServiceImpl(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity save(AuthorEntity author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream(authorRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);

        return authorRepository.findById(id).map(existingAuthorEntity -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthorEntity::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthorEntity::setAge);

            return authorRepository.save(existingAuthorEntity);
        }).orElseThrow(() -> new RuntimeException("Author does not exist"));

    }

    @Override
    public AuthorEntity delete(Long id) {
        Optional<AuthorEntity> returnedDeletedAuthor = authorRepository.findById(id);
        authorRepository.deleteById(id);

        return returnedDeletedAuthor.orElse(null);
    }

}
