package ru.gb.springbootlesson2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson2.controllers.reader.ReaderRequest;
import ru.gb.springbootlesson2.entity.Reader;
import ru.gb.springbootlesson2.repository.ReaderRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public Reader createReader(ReaderRequest readerRequest) {
        if (readerRequest == null) {
            throw new NullPointerException();
        }
        return readerRepository.add(new Reader(readerRequest.getName()));
    }

    public Reader getReader(long id) {
        if (readerRepository.findById(id) == null) {
            throw new NoSuchElementException("Не удалось найти читателя с id = " + id);
        }
        return readerRepository.findById(id);
    }

    public void deleteReader(long id) {
        if (readerRepository.findById(id) == null) {
            throw new NoSuchElementException("Не удалось найти читателя с id = " + id);
        }
        readerRepository.deleteById(id);
    }
}
