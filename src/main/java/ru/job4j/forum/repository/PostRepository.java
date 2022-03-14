package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

import java.util.Collection;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findById(int id);

    @Query("select distinct p from Post p join fetch p.user u where u.username=?1")
    Collection<Post> findPostsByUser(String userName);
}
