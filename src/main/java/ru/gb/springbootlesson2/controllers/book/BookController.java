package ru.gb.springbootlesson2.controllers.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson2.dto.BookRequest;
import ru.gb.springbootlesson2.entity.Book;
import ru.gb.springbootlesson2.services.BookService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(bookService.getBook(id));
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        try {
            bookService.deleteBook(id);
        } catch (NoSuchElementException e) {
            log.info("В репозитории нет книги с id = {} = ", id);
        }
    }

    @PostMapping()
    public ResponseEntity<Book> addBook(@RequestBody BookRequest bookRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookRequest));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
