package com.spritle.User_auth.service;

import com.spritle.User_auth.model.User;
import com.spritle.User_auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        if(userRepository.findById(user.getEmail()).isPresent()){
            System.out.println("user already registered");
            return false;
        }else{
            user.setRole("student");
            User user1 = userRepository.save(user);
            System.out.println("user registered successfully");
            return true;
        }
    }

    @Override
    public User login(String email, String pass) {
        if(userRepository.findById(email).isPresent()){
            User user = userRepository.findById(email).get();
            if(user.getPassword().equals(pass)){
                return user;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }
}
