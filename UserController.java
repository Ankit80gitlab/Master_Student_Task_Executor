package com.spritle.User_auth.controller;

import com.spritle.User_auth.model.User;
import com.spritle.User_auth.security.SecurityTokenGenerator;
import com.spritle.User_auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/app/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user){
        User result = userService.login(user.getEmail(), user.getPassword());
        if (result != null) {
            Map<String, String> key = securityTokenGenerator.generateToken(result);
            return new ResponseEntity<>(key,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.NOT_FOUND);
        }
    }
}
