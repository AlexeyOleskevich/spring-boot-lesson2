package ru.gb.springbootlesson2.entity;

import lombok.Data;

@Data
public class Reader {
    private static long genId;

    private final long id;
    private final String name;

    public Reader(String name) {
        this.id = genId++;
        this.name = name;
    }
}
