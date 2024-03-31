package ru.gb.springbootlesson2.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson2.controllers.issue.IssueResponse;
import ru.gb.springbootlesson2.entity.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class IssueRepository {
    private List<Issue> list = new ArrayList<>();

    public void createIssue(Issue issue) {
        list.add(issue);
    }

    public List<Issue> getAllIsues() {
        return List.copyOf(list);
    }

    public Issue findById(long id) {
        return list.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean isReaderHaveBook(long readerId) {
        return list.stream()
                .anyMatch(e -> e.getIdReader() == readerId);
    }

    public List<Issue> getAllIssuesForReader(long readerId) {
        return list.stream()
                .filter(e -> e.getIdReader() == readerId)
                .toList();
    }

    public void returnBook(long issueId) {
        Issue issue = list.stream().filter(e -> e.getId() == issueId)
                .findFirst()
                .orElse(null);
        if (issue == null) {
            throw new NoSuchElementException("Выдача с id = " + issueId + " не была найдена.");
        }
        issue.setReturnedAt(LocalDateTime.now());
    }

}
