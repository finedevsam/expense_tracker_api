package com.samson.expensetrackerapi.controller;

import com.samson.expensetrackerapi.entity.User;
import com.samson.expensetrackerapi.entity.UserModel;
import com.samson.expensetrackerapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserModel user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id){
        return new ResponseEntity<User>(userService.readUser(id), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.updateUser(userModel,id), HttpStatus.OK);
    }
}
