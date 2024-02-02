package com.afdevelopment.biblioteca.controller;

import com.afdevelopment.biblioteca.model.Book;
import com.afdevelopment.biblioteca.request.GetBook;
import com.afdevelopment.biblioteca.response.DetailResponse;
import com.afdevelopment.biblioteca.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    private final String OPCORRECTA = "Operación correcta";
    private final String DETAIL = "detailResponse";
    private final String SUCCESSCODE = "SUC-01";
    private final String BOOKDETAIL = "bookDetail";
    private final BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/id/{bookId}")
    public ResponseEntity<Map<String, Object>> bookById(@PathVariable Integer bookId){
        logger.info("Inicia controlador de búsqueda de libros por id");
        Book book = bookService.findById(bookId);
        DetailResponse responseOk = new DetailResponse();
        responseOk.setCode(SUCCESSCODE);
        responseOk.setBussinessMeaning(OPCORRECTA);
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put(DETAIL, responseOk);
        jsonResponse.put(BOOKDETAIL, book);
        logger.info("Finaliza controlador de búsqueda de libros por id");
        return (new ResponseEntity<>(jsonResponse, new HttpHeaders(), HttpStatus.OK));
    }
    @GetMapping("/isbn/{bookISBN}")
    public ResponseEntity<Map<String, Object>> bookByISBN(@PathVariable String bookISBN){
        logger.info("Inicia controlador de búsqueda de libros por ISBN");
        Book book = bookService.findByISBN(bookISBN);
        DetailResponse responseOk = new DetailResponse();
        responseOk.setCode(SUCCESSCODE);
        responseOk.setBussinessMeaning(OPCORRECTA);
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put(DETAIL, responseOk);
        jsonResponse.put(BOOKDETAIL, book);
        logger.info("Finaliza controlador de búsqueda de libros por ISBN");
        return (new ResponseEntity<>(jsonResponse, new HttpHeaders(), HttpStatus.OK));
    }

    @PostMapping("/author")
    public ResponseEntity<Map<String, Object>> booksByAuthor(@RequestBody GetBook getBook){
        logger.info("Inicia controlador de búsqueda de libros por autor");
        List<Book> books = bookService.findAllByAuthor(getBook.getAuthor());
        DetailResponse responseOk = new DetailResponse();
        responseOk.setCode(SUCCESSCODE);
        responseOk.setBussinessMeaning(OPCORRECTA);
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put(DETAIL, responseOk);
        jsonResponse.put(BOOKDETAIL, books);
        logger.info("Finaliza controlador de búsqueda de libros por autor");
        return new ResponseEntity<>(jsonResponse, new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity<Map<String, Object>> saveBook(@RequestBody Book book){
        logger.info("Inicia controlador de guardado de libros");
        Book bookResponse = bookService.saveBook(book);
        DetailResponse responseOk = new DetailResponse();
        responseOk.setCode(SUCCESSCODE);
        responseOk.setBussinessMeaning(OPCORRECTA);
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put(DETAIL, responseOk);
        jsonResponse.put(BOOKDETAIL, bookResponse);
        logger.info("Finaliza controlador de guardado de libros");
        return (new ResponseEntity<>(jsonResponse, new HttpHeaders(), HttpStatus.OK));
    }
}
