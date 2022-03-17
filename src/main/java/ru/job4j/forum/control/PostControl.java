package ru.job4j.forum.control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    private final PostService postService;
    private final UserRepository userRepository;

    public PostControl(PostService postService, UserRepository userRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
    }

    @GetMapping("/post")
    public String post(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", postService.findPostById(id));
        return "post";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        post.setUser(user);
        post.setUsername(user.getUsername());
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", postService.findPostById(id));
        return "edit";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        postService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/myPosts")
    public String myPosts(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("posts", postService.findByUser(auth.getName()));
        return "myPosts";
    }
}
