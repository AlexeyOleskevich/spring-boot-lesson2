package ru.gb.springbootlesson2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "issues")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "idReader")
    private long idReader;

    @Column(name = "idBook")
    private long idBook;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime issuedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returnedAt;

    public Issue(long idReader, long idBook) {
        this.idReader = idReader;
        this.idBook = idBook;
        issuedAt = LocalDateTime.now();
    }
}
