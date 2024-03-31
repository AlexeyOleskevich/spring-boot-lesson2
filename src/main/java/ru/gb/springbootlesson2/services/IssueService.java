package ru.gb.springbootlesson2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson2.controllers.issue.IssueRequest;
import ru.gb.springbootlesson2.controllers.issue.IssueResponse;
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
public class IssueService {
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    @Value("${spring.application.issue.max-allowed-books:1}")
    private int maxAllowedBooks;

    public Issue createIssue(IssueRequest request) {
        if (bookRepository.findById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }

        if (readerRepository.findById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }

        List<Issue> issues = issueRepository.getAllIssuesForReader(request.getReaderId());

        System.out.println(maxAllowedBooks);
        if (issues.size() >= maxAllowedBooks) {
            throw new RuntimeException("Пользователь с id = " + request.getReaderId() + " уже имеет больше " + maxAllowedBooks + " книг.");
        }

        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        if (issueRepository.getAllIsues().contains(issue)) {
            throw new RuntimeException("Такая выдача уже существует!");
        }
        issueRepository.createIssue(issue);
        return issue;
    }

    public Issue getIssue(long id) {
        if (issueRepository.findById(id) == null) {
            throw new NoSuchElementException("Не удалось найти выдачу с id " + id);
        }
        return issueRepository.findById(id);
    }

    public void returnBook(long issueId) {
        issueRepository.returnBook(issueId);
    }

    public List<IssueResponse> getAllIssues() {
        List<IssueResponse> result = new ArrayList<>();
        List<Issue> issues = issueRepository.getAllIsues();

        for (Issue issue : issues) {
            IssueResponse issueResponse = getIssueResponse(issue);
            result.add(issueResponse);
        }
        return result;
    }

    private IssueResponse getIssueResponse(Issue issue) {
        Book book = bookRepository.findById(issue.getIdBook());
        Reader reader = readerRepository.findById(issue.getIdReader());
        IssueResponse issueResponse = new IssueResponse(book.getName(), reader.getName(), issue.getIssuedAt(), issue.getReturnedAt());
        return issueResponse;
    }

}
