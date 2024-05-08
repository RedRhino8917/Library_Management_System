package org.gfg.minor1.repository;

import org.gfg.minor1.model.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    public void setup(){
        author = Author.builder().id(1).email("authorh2@gmail.com").build();
        authorRepository.save(author); // save the data in h2 database

    }


    @Test
    public void testFindByEmail(){
        Author a = authorRepository.findByEmail("authorh2@gmail.com");
        Assertions.assertEquals(author.getEmail(), a.getEmail());
    }


}
