package ru.gb.springbootlesson2.controllers.reader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson2.entity.Issue;
import ru.gb.springbootlesson2.entity.Reader;
import ru.gb.springbootlesson2.services.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reader")
@RequiredArgsConstructor
@Slf4j
public class ReaderController {
    private final ReaderService readerService;

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(readerService.getReader(id));
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{readerId}/issue")
    public ResponseEntity<List<Issue>> getReaderIssues(@PathVariable long readerId) {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(readerService.getAllIssuesForReader(readerId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable long id) {
        try {
            readerService.deleteReader(id);
        } catch (NoSuchElementException e) {
            log.info("В репозитории нет читателя с id = {} = ", id);
        }
    }

    @PostMapping()
    public ResponseEntity<Reader> addReader(@RequestBody ReaderRequest readerRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(readerService.createReader(readerRequest));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
