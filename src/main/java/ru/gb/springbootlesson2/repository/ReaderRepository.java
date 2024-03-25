package ru.gb.springbootlesson2.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson2.entity.Book;
import ru.gb.springbootlesson2.entity.Reader;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderRepository {
    private List<Reader> list = new ArrayList<>();

    public ReaderRepository() {
        list.add(new Reader("Костя"));
        list.add(new Reader("Василий"));
        list.add(new Reader("Семен"));
    }

    public Reader add(Reader reader) {
        list.add(reader);
        return reader;
    }

    public Reader findById(long id) {
        return list.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteById(long id) {
        list.removeIf(e -> e.getId() == id);
    }
}
