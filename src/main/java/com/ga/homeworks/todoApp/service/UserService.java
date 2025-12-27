package com.ga.homeworks.todoApp.service;

import com.ga.homeworks.todoApp.exception.RecordExistsException;
import com.ga.homeworks.todoApp.model.User;
import com.ga.homeworks.todoApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    @Lazy
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        if (userRepository.existsByEmailAddress(user.getEmailAddress())) {
            throw new RecordExistsException("User with email " + user.getEmailAddress() + " already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
