package com.igrejasobrenatural.services;

import com.igrejasobrenatural.domain.user.User;

public interface TokenService {
    String generateToken(User user);
    String validateToken(String token);
}
