package org.gfg.minor1.repository;
import org.gfg.minor1.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query(value = "select * from author where email =:email", nativeQuery = true) // mysql
    Author getAuthor(String email);

    // 2nd way of writing the query
    @Query("select a from Author a where a.email =:email") // hibernate
    Author getAuthorWithoutNative(String email);


    // 3rd way of writing the query
    Author findByEmail(String email);  // we have to follow some(way of writing the method name) rules, hibernate will create my query
}
