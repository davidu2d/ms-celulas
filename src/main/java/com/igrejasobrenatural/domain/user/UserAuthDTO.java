package com.igrejasobrenatural.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record UserAuthDTO(@NotBlank String username, @NotBlank String password){}
