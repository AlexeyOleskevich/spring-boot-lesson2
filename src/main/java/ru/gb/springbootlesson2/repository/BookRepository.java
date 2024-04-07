package ru.gb.springbootlesson2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springbootlesson2.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByName(String name);
}
