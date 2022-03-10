package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }


    public Collection<Post> findAllPosts() {
        List<Post> res = new ArrayList<>();
        repository.findAll().forEach(res::add);
        return res;
    }

    public Post findPostById(int id) {
        return repository.findById(id);
    }

    public void save(Post post) {
        post.setTime(LocalDateTime.now());
        repository.save(post);
    }
}
