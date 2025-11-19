package com.ecommerce.Service;

import com.ecommerce.Model.user;

import com.ecommerce.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class userService {

    @Autowired
    private userRepo userRepository;


    public user register(user user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User already registered");
        }
        return userRepository.save(user);  // This saves the user to the database
    }


    public user login(String email, String password) {
        user user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public List<user> getAllUsers() {
        return userRepository.findAll();
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
