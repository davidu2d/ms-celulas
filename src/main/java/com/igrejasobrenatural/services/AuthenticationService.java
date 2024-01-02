package com.igrejasobrenatural.services;

import com.igrejasobrenatural.domain.token.TokenResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails loadUserByUsername(String username);

    TokenResponseDTO authenticate(String username, String password);
}
