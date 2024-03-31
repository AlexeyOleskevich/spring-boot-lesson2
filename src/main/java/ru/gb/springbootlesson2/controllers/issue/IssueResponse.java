package ru.gb.springbootlesson2.controllers.issue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class IssueResponse {
    public String bookTitle;
    public String readerName;
    public LocalDateTime issuedAt;
    public LocalDateTime returnedAt;
}
