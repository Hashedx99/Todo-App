package com.ga.homeworks.todoApp.service;

import com.ga.homeworks.todoApp.exception.RecordExistsException;
import com.ga.homeworks.todoApp.model.LoginRequest;
import com.ga.homeworks.todoApp.model.LoginResponse;
import com.ga.homeworks.todoApp.model.User;
import com.ga.homeworks.todoApp.repository.UserRepository;
import com.ga.homeworks.todoApp.security.JWTUtil;
import com.ga.homeworks.todoApp.security.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    private final JWTUtil jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtil jwtUtils,
                       @Lazy AuthenticationManager authenticationManager,
                       @Lazy MyUserDetails myUserDetails){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }


    public User createUser(User user) {
        if (userRepository.existsByEmailAddress(user.getEmailAddress())) {
            throw new RecordExistsException("User with email " + user.getEmailAddress() + " already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new UsernameNotFoundException("User " +
                "not found with email: " + emailAddress));
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(),
                            loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            myUserDetails = (MyUserDetails) authentication.getPrincipal();

            final String JWT = jwtUtils.generateToken(myUserDetails);

            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e)  {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
