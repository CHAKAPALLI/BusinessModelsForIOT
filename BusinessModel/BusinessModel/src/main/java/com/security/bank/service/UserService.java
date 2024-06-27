package com.security.bank.service;

import com.security.bank.dto.AdminDto;
import com.security.bank.dto.UserDto;
import com.security.bank.entity.*;
import com.security.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;
    public void registerUser(UserDto userDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userDto.getPassword());
        Role role = new Role();
        role.setRoleName("ROLE_CUSTOMER");
        User saveUser = new User();
        saveUser.setName(userDto.getName());
        saveUser.setPassword(encodedPassword);
        saveUser.setUsername(userDto.getUsername());
        saveUser.setEmail(userDto.getEmail());
        saveUser.setRoles(role);
        userRepository.save(saveUser);
    }

    public void registerAdmin(AdminDto adminDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(adminDto.getPassword());
        Role role = new Role();
        role.setRoleName("ROLE_ADMIN");
        User saveUser = new User();
        saveUser.setName(adminDto.getName());
        saveUser.setPassword(encodedPassword);
        saveUser.setUsername(adminDto.getUsername());
        saveUser.setEmail(adminDto.getEmail());
        saveUser.setRoles(role);
        userRepository.save(saveUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByName(String username) {
        return userRepository.findByUsername(username).get();
    }

    public String deleteUserById(Long userId) {
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return "Deleted Successfully";
        }
         return "Error in deletion";
    }

   


}
