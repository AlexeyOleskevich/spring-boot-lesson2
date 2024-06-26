package ru.gb.springbootlesson2.controllers.issue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson2.dto.IssueRequest;
import ru.gb.springbootlesson2.entity.Issue;
import ru.gb.springbootlesson2.services.IssueService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("issue")
public class IssueController {

    private IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssue(@PathVariable long id) {
        log.info("Поступил запрос на выдачу с id = {}", id);
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(service.getIssue(id));
        } catch (NoSuchElementException e) {
            log.info("Выдачи с id = " + id + " нет в базе данных.");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Issue> createIssue (@RequestBody IssueRequest issueRequest) {
        log.info("Поступил запрос на выдачу: readerId={}, bookId={}", issueRequest.getReaderId(), issueRequest.getBookId());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createIssue(issueRequest));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("{issueId}")
    public ResponseEntity<Void> closeIssue(@PathVariable long issueId) {
        try {
            service.returnBook(issueId);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            log.info("Выдачи с id = {} нет в базе данных.", issueId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

