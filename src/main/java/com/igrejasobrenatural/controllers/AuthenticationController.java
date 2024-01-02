package com.igrejasobrenatural.controllers;

import com.igrejasobrenatural.domain.user.UserAuthDTO;
import com.igrejasobrenatural.domain.user.UserRegisterDTO;
import com.igrejasobrenatural.services.AuthenticationService;
import com.igrejasobrenatural.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    public ResponseEntity token(@RequestBody @Valid UserAuthDTO userAuth){
        var token = this.authenticationService.authenticate(userAuth.username(), userAuth.password());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO userRegister){
        this.userService.register(userRegister.toUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
