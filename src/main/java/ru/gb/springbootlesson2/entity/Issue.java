package ru.gb.springbootlesson2.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Data
public class Issue {
    private static long genId;

    private final long id;
    private final long idReader;
    private final long idBook;
    private final LocalDateTime issuedAt;
    private LocalDateTime returnedAt;

    public Issue(long idReader, long idBook) {
        id = genId++;
        this.idReader = idReader;
        this.idBook = idBook;
        issuedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (idReader != issue.idReader) return false;
        return idBook == issue.idBook;
    }

    @Override
    public int hashCode() {
        int result = (int) (idReader ^ (idReader >>> 32));
        result = 31 * result + (int) (idBook ^ (idBook >>> 32));
        return result;
    }
}
