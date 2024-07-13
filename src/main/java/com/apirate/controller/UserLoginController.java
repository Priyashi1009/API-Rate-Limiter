package com.apirate.controller;

import com.apirate.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserLoginController {

    @Autowired
    private UserLoginService userService;

    @GetMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        return userService.loginService(username, password);
    }

    @GetMapping("/getRestaurent")
    public Map<String, Object> getRestaurent(@RequestParam String token) {
        return userService.validateJwtToken(token); 
    }
}
