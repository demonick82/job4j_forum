package ru.job4j.forum.control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;


@Controller
public class IndexControl {
    private final PostService postService;

    public IndexControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping({"/"})
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("name=" + auth.getName());
        model.addAttribute("posts", postService.findAllPosts());
        model.addAttribute("username", auth.getName());
        return "/index";
    }

}
