package com.afdevelopment.biblioteca.repository;

import com.afdevelopment.biblioteca.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findBookById(Integer id);
    List<Book> findAllByAuthor(String author);

}
