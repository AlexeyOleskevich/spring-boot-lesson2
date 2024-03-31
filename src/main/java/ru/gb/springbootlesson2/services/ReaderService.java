package ru.gb.springbootlesson2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson2.controllers.reader.ReaderRequest;
import ru.gb.springbootlesson2.entity.Book;
import ru.gb.springbootlesson2.entity.Issue;
import ru.gb.springbootlesson2.entity.Reader;
import ru.gb.springbootlesson2.repository.BookRepository;
import ru.gb.springbootlesson2.repository.IssueRepository;
import ru.gb.springbootlesson2.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.getAllReaders();
    }

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

    public List<Issue> getAllIssuesForReader(long readerId) {
        List<Issue> issuesForReader = issueRepository.getAllIssuesForReader(readerId);

        if (issuesForReader == null) {
            throw new NoSuchElementException("Не удалось найти выдачи для читателя с id = " + readerId);
        }
        return issuesForReader;
    }

    public List<Book> getAllBooksForReader(long readerId) {
        List<Issue> issues = getAllIssuesForReader(readerId);
        List<Book> result = new ArrayList<>();

        for (Issue issue : issues) {
            if (issue.getReturnedAt() == null) {
                result.add(bookRepository.findById(issue.getIdBook()));
            }
        }
        return result;
    }
}
