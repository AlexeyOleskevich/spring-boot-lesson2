package ru.gb.springbootlesson2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springbootlesson2.entity.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long>, CustomIssueRepository {

    List<Issue> findIssuesByIdReader(long readerId);

    boolean existsByIdReaderAndIdBook(long idReader, long idBook);
}
