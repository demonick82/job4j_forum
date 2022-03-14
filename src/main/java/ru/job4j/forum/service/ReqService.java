package ru.job4j.forum.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.UserRepository;

@Service
public class ReqService {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;


    public ReqService(PasswordEncoder encoder, UserRepository users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    public String getPassword(User user) {
        return encoder.encode(user.getPassword());
    }

    public Authority getAuthority(String role) {
        return authorities.findByAuthority(role);
    }

    public void save(User user) {
        users.save(user);
    }

    public boolean userNameCheck(String name) {
        return users.findByUsername(name) == null;
    }
}
