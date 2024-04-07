package ru.gb.springbootlesson2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "readers")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public Reader(String name) {
        this.name = name;
    }
}
