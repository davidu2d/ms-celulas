package com.igrejasobrenatural.services.impl;

import com.igrejasobrenatural.domain.token.TokenResponseDTO;
import com.igrejasobrenatural.domain.user.User;
import com.igrejasobrenatural.repositories.UserRepository;
import com.igrejasobrenatural.services.AuthenticationService;
import com.igrejasobrenatural.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements UserDetailsService, AuthenticationService {

    private final UserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public TokenResponseDTO authenticate(String username, String password){
        var usernamePassword = new UsernamePasswordAuthenticationToken(username, password);
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((User) auth.getPrincipal());
        return TokenResponseDTO.builder().token(token).build();
    }

}
