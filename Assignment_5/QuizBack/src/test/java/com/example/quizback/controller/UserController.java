package com.example.quizback.controller;

import com.example.quizback.model.User;
import com.example.quizback.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl loginService){
        this.userService = loginService;
    }

    @PostMapping("/signup")
    public Boolean signup(@RequestBody User user){
        return userService.signup(user);
    }

    @PostMapping("/login")
    public User login(@RequestParam("username") String username, @RequestParam("password") String password){

        return userService.login(username, password);
    }

    @PostMapping("/edit")
    public Boolean editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

    @PostMapping("/logout")
    public String logout(@RequestBody User user){
        return userService.logout(user);
    }
}
