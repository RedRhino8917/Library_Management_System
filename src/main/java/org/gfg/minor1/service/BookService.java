package org.gfg.minor1.service;

import org.gfg.minor1.model.Author;
import org.gfg.minor1.model.Book;
import org.gfg.minor1.model.BookType;
import org.gfg.minor1.model.Operator;
import org.gfg.minor1.repository.AuthorRepository;
import org.gfg.minor1.repository.BookRepository;
import org.gfg.minor1.repository.RedisDataRepository;
import org.gfg.minor1.request.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.FilterType;
import org.gfg.minor1.model.BookFilterType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RedisDataRepository redisDataRepository;

    @Autowired
    private AuthorRepository authorRepository;
    public Book createBook(BookCreateRequest bookCreateRequest) {
        // check if author, which is coming to me from front-end is already present in my db or not
        // if not present, add author into db

        // otherwise i will not add one more row inside my table
        Author authorFromDB = authorRepository.findByEmail(bookCreateRequest.getAuthorEmail());
        if(authorFromDB == null){
            // create a row inside the author table
            authorFromDB = authorRepository.save(bookCreateRequest.toAuthor());

        }
        // create a row inside my book
        Book book = bookCreateRequest.toBook();
        book.setAuthor(authorFromDB);
        book = bookRepository.save(book);
        // push the data into redis as well
        redisDataRepository.setBookToRedis(book);
        return book;
    }

    public List<Book> filter(BookFilterType filterBy, Operator operator, String value) {
        switch (operator){
            case EQUALS :
                switch (filterBy){
                    case BOOK_NO:
                        // we need to check if data is present in redis or not
                        List<Book> list = redisDataRepository.getBookByBookNo(value);
                        if(list!= null && !list.isEmpty()){
                            return list;
                        }
                        list = bookRepository.findByBookNo(value);
                        if(!list.isEmpty()){
                            redisDataRepository.setBookToRedisByBookNo(list.get(0));
                        }
                        return list;
                    case AUTHOR_NAME:
                        return bookRepository.findByAuthorName(value);
                    case COST:
                        return bookRepository.findByCost(Integer.valueOf(value));
                    case BOOKTYPE:
                        return bookRepository.findByType(BookType.valueOf(value));
                }
            case LESS_THAN:
                switch (filterBy){
                    case COST:
                        return bookRepository.findByCostLessThan(Integer.valueOf(value));
                }
            default:
                return new ArrayList<>();
        }
    }
    public void saveUpdate(Book book){
        bookRepository.save(book);
    }
}
