package com.igrejasobrenatural.services;

import com.igrejasobrenatural.domain.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    void register(User user);
}
