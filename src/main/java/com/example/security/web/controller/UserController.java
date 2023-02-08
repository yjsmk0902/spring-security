package com.example.security.web.controller;

import com.example.security.domain.dto.UserDto;
import com.example.security.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public String signup(UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";
    }
}
