package ru.gb.springbootlesson2.dto;

import lombok.Data;

@Data
public class IssueRequest {
    private long readerId;
    private long bookId;
}
