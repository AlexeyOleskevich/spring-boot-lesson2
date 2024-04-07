package ru.gb.springbootlesson2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson2.dto.ReaderRequest;
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
        return readerRepository.findAll();
    }

    public Reader createReader(ReaderRequest readerRequest) {
        if (readerRequest == null) {
            throw new NullPointerException();
        }

        if (readerRepository.existsByName(readerRequest.getName())){
            throw new RuntimeException("Читатель с таким именем уже есть в базе данных.");
        }
        return readerRepository.save(new Reader(readerRequest.getName()));
    }

    public Reader getReader(long id) {
        return readerRepository.findById(id).orElseThrow();
    }

    public void deleteReader(long id) {
        readerRepository.deleteById(id);
    }

    public List<Issue> getAllIssuesForReader(long readerId) {
        List<Issue> issuesForReader = issueRepository.findIssuesByIdReader(readerId);

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
                result.add(bookRepository.findById(issue.getIdBook()).orElseThrow());
            }
        }
        return result;
    }
}
