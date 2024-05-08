package org.gfg.minor1.repository;

import org.gfg.minor1.model.Book;
import org.gfg.minor1.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByBookNo(String bookNo);
    List<Book> findByAuthorName(String value);
    List<Book> findByCost(Integer integer);
    List<Book> findByType(BookType bookType);

    List<Book> findByCostLessThan(Integer integer);
}
