package ru.gb.springbootlesson2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson2.controllers.issue.IssueRequest;
import ru.gb.springbootlesson2.entity.Issue;
import ru.gb.springbootlesson2.repository.BookRepository;
import ru.gb.springbootlesson2.repository.IssueRepository;
import ru.gb.springbootlesson2.repository.ReaderRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class IssueService {
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    public Issue createIssue(IssueRequest request) {
        if (bookRepository.findById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }

        if (readerRepository.findById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }


        if (isReaderHaveBook(request.getReaderId())) {
            throw new RuntimeException("Пользователь с id = " + request.getReaderId() + " уже имеет книгу");
        }

        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        issueRepository.createIssue(issue);
        return issue;
    }

    public Issue getIssue(long id) {
        if (issueRepository.findById(id) == null) {
            throw new NoSuchElementException("Не удалось найти выдачу с id " + id);
        }
        return issueRepository.findById(id);
    }

    private boolean isReaderHaveBook(long readerId) {
        return issueRepository.isReaderHaveBook(readerId);
    }

}
