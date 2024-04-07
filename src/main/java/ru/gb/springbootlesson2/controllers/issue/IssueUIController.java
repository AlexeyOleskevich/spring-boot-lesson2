package ru.gb.springbootlesson2.controllers.issue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootlesson2.dto.IssueResponse;
import ru.gb.springbootlesson2.services.IssueService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("ui")
public class IssueUIController {
    private final IssueService issueService;

    @GetMapping("issues")
    public String getAllIssues(Model model) {
        List<IssueResponse> issues = issueService.getAllIssues();
        System.out.println(issues);
        model.addAttribute("issues", issues);
        return "issueList";
    }
}
