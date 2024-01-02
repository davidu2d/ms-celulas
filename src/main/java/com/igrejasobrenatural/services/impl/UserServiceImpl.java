package com.igrejasobrenatural.services.impl;

import com.igrejasobrenatural.domain.user.User;
import com.igrejasobrenatural.exceptions.ResourceAlreadyExistsException;
import com.igrejasobrenatural.repositories.UserRepository;
import com.igrejasobrenatural.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public Optional<User> findByUsername(String username){
        return Optional.ofNullable(this.repository.findByUsername(username));
    }

    private void validIfUserAlreadyExists(String username){
        var user =  findByUsername(username);
        if (user.isPresent()) throw new ResourceAlreadyExistsException(User.class);
    }

    private void encryptUserPassword(User user) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
    }

    public void register(User user) {
        log.info("Register new User with username: {}", user.getUsername());
        validIfUserAlreadyExists(user.getUsername());
        encryptUserPassword(user);
        this.repository.save(user);
    }
}
