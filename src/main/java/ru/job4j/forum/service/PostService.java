package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostMem;

import java.util.Collection;

@Service
public class PostService {
    private final PostMem postMem;

    public PostService(PostMem postMem) {
        this.postMem = postMem;
    }

    public Collection<Post> findAllPosts() {
        return postMem.findAllPosts();
    }

    public Post findPostById(int id) {
        return postMem.findPostById(id);
    }

    public void save(Post post) {
        postMem.save(post);
    }
}
