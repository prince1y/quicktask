package com.prins1y.quicktask.service;

import com.prins1y.quicktask.dto.RegisterRequest;
import com.prins1y.quicktask.repository.UserRepository;
import com.prins1y.quicktask.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequest request) {

        // user input validation
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "This email already exists";
        }

        //user entity creation
        User user = User.builder()
                  .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        return "User registered successfully";


    }
}
