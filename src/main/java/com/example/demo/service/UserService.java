package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.ERole;
import com.example.demo.exceptions.UserExistingException;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignupRequest userIn) throws UserExistingException {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setFirstname(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception ex) {
            LOG.error("Error during registration. {}", ex.getMessage());
            throw new UserExistingException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }

    public User updateUser(UserDTO userDTO, Principal principal) {

        User user = getUserbyPrincipal(principal);
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        user.setBio(userDTO.getBio());

        return userRepository.save(user);
    }

    public User getCurrentUser(Principal principal) {
        return getUserbyPrincipal(principal);
    }

    private User getUserbyPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
