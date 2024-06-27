package com.security.bank.controller;
import com.security.bank.dto.AdminDto;

import com.security.bank.entity.User;
import com.security.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService adminUserService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAdmin(@RequestBody AdminDto admin)
    {
        adminUserService.registerAdmin(admin);
    }

    @GetMapping("/getAllUser")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUser(){
        return adminUserService.getAllUsers();
    }

    @GetMapping("/getUserByName/{username}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserByName(@PathVariable String username){
        return adminUserService.getUserByName(username);
    }

    @DeleteMapping("/deleteUser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUserById(@PathVariable Long userId){
        return adminUserService.deleteUserById(userId);
    }


}
