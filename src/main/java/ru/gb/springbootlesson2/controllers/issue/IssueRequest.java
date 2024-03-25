package ru.gb.springbootlesson2.controllers.issue;

import lombok.Data;

@Data
public class IssueRequest {
    private long readerId;
    private long bookId;
}
