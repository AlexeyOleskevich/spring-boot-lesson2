package ru.gb.springbootlesson2.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springbootlesson2.entity.Issue;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Repository
public class CustomIssueRepositoryImpl implements CustomIssueRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void returnBook(long issueId) {
        Issue issue = entityManager.find(Issue.class, issueId);
        if (issue == null) {
            throw new NoSuchElementException("Выдача с id = " + issueId + " не была найдена.");
        }
        issue.setReturnedAt(LocalDateTime.now());
        entityManager.merge(issue);

    }
}
