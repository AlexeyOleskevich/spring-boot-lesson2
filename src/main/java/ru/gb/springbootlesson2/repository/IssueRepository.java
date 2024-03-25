package ru.gb.springbootlesson2.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson2.entity.Issue;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IssueRepository {
    private List<Issue> list = new ArrayList<>();

    public void createIssue(Issue issue) {
        list.add(issue);
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
}
