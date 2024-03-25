package ru.gb.springbootlesson2.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson2.entity.Book;


import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> list = new ArrayList<>();

   public BookRepository() {
       list.add(new Book("Война и мир"));
       list.add(new Book("Мастер и Маргарита"));
       list.add(new Book("Приключения Буратино"));
       list.add(new Book("Алиса в стране чудес"));
   }

   public Book add(Book book) {
       list.add(book);
       return book;
   }

    public Book findById(long id) {
        return list.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteById(long id) {
       list.removeIf(e -> e.getId() == id);
    }

}
