package ru.gb.springbootlesson2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson2.controllers.book.BookRequest;
import ru.gb.springbootlesson2.entity.Book;
import ru.gb.springbootlesson2.repository.BookRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(BookRequest bookRequest) {
        if (bookRequest == null) {
            throw new NullPointerException();
        }
        return bookRepository.add(new Book(bookRequest.getName()));
    }

    public Book getBook(long id) {
        if (bookRepository.findById(id) == null) {
            throw new NoSuchElementException("Не удалось найти книгу с id = " + id);
        }
        return bookRepository.findById(id);
    }

    public void deleteBook(long id) {
        if (bookRepository.findById(id) == null) {
            throw new NoSuchElementException("Не удалось найти книгу с id = " + id);
        }
        bookRepository.deleteById(id);
    }
}
