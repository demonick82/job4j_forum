package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;
    private final UserRepository userRepository;

    public PostService(PostRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }


    public Collection<Post> findAllPosts() {
        List<Post> res = new ArrayList<>();
        repository.findAll().forEach(res::add);
        return res;
    }

    public Post findPostById(int id) {
        return repository.findById(id);
    }

    public void save(Post post, String userName) {
        post.setTime(LocalDateTime.now());
        post.setUser(userRepository.findByUsername(userName));
        repository.save(post);
    }

    public void deletePost(int id) {
        repository.deleteById(id);
    }

    public Collection<Post> findByUser(String name) {
        return repository.findPostsByUser(name);
    }
}
