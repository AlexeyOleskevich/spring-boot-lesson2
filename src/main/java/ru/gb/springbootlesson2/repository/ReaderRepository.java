package ru.gb.springbootlesson2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springbootlesson2.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    boolean existsByName(String name);
}
