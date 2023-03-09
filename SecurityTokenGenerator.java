package com.spritle.User_auth.security;

import com.spritle.User_auth.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String,String> generateToken(User user);
}
