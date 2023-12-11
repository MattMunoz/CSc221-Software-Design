package com.example.quizback.service.impl;

import com.example.quizback.model.User;
import com.example.quizback.repository.UserRepository;
import com.example.quizback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signup(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null)
            return user;

        return null;
    }

    @Override
    public boolean editUser(User user){
        try {
            Optional<User> dbUserOptional = userRepository.findById(user.getId());
            User dbUser = dbUserOptional.get();
            dbUser.setFullName(user.getFullName());
            dbUser.setUsername(user.getUsername());
            dbUser.setPassword(user.getPassword());
            dbUser.setDob(user.getDob());

            userRepository.save(dbUser);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public String logout(User user) {
        return "User " + user.getFullName() + " out!";
    }
}