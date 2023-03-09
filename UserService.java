package com.spritle.User_auth.service;

import com.spritle.User_auth.model.User;

public interface UserService {

    public boolean addUser(User user);
    public User login(String userEmail, String passwd);

}
