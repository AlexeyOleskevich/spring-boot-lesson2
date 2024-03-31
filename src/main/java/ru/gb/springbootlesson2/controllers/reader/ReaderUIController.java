package ru.gb.springbootlesson2.controllers.reader;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootlesson2.services.ReaderService;

@Controller
@RequestMapping("ui")
@RequiredArgsConstructor
public class ReaderUIController {
    private final ReaderService readerService;

    @GetMapping("reader")
    public String getAllReaders(Model model) {
        model.addAttribute("readerList", readerService.getAllReaders());
        return "readerList";
    }

    @GetMapping("reader/{id}")
    public String getReaderBooks(@PathVariable long id, Model model) {
        model.addAttribute("readerName", readerService.getReader(id).getName());
        model.addAttribute("readerBooks", readerService.getAllBooksForReader(id));
        return "readerBooksList";
    }

}
