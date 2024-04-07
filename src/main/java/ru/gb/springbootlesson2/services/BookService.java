package ru.gb.springbootlesson2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson2.dto.BookRequest;
import ru.gb.springbootlesson2.entity.Book;
import ru.gb.springbootlesson2.repository.BookRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(BookRequest bookRequest) {
        if (bookRequest == null) {
            throw new NullPointerException();
        }

        if (bookRepository.existsByName(bookRequest.getName())){
            throw new RuntimeException("Книга с таким названием уже есть в базе данных.");
        }
        return bookRepository.save(new Book(bookRequest.getName()));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
