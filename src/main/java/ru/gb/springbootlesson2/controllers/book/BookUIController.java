package ru.gb.springbootlesson2.controllers.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootlesson2.services.BookService;

@Controller
@RequestMapping("ui")
@RequiredArgsConstructor
public class BookUIController {
    private final BookService bookService;

    @GetMapping("books")
    public String getAllBooks(Model model) {
        model.addAttribute("bookList", bookService.getAllBooks());
        return "bookList.html";
    }
}
