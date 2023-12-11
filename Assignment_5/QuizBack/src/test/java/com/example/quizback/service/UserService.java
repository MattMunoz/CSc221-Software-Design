package com.example.quizback.service;

import com.example.quizback.model.User;

public interface UserService {

    User login(String username, String password);

    String logout(User user);

    boolean editUser(User user);

    boolean signup(User user);
}