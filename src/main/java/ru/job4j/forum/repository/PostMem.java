package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMem {
    private final Map<Integer, Post> posts = new HashMap<>();

    private static final AtomicInteger ID = new AtomicInteger(2);

    public PostMem() {
        Post post1 = Post.of("Post1", "Lorem ipsum dolor sit amet, consectetur"
                + " adipisicing elit. Adipisci aperiam autem dignissimos dolorem earum enim ex hic inventore, ");
        post1.setId(1);
        post1.setTime(LocalDateTime.now());
        Post post2 = Post.of("Post2", "Lorem ipsum dolor sit amet, consectetur"
                + " adipisicing elit. Adipisci aperiam autem dignissimos dolorem earum enim ex hic inventore, ");
        post2.setId(2);
        post2.setTime(LocalDateTime.now());

        posts.put(1, post1);
        posts.put(2, post2);
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Post findPostById(int id) {
        return posts.get(id);
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(ID.incrementAndGet());
        }
        post.setTime(LocalDateTime.now());
        posts.put(post.getId(), post);
    }
}
