package com.spritle.User_auth.security;

import com.spritle.User_auth.model.User;
import com.spritle.User_auth.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {

    @Autowired
    UserRepository userRepository;

    @Override
    public Map<String, String> generateToken(User user) {
        Map<String,String> map = new HashMap<>();

        Map<String,Object> userData=new HashMap<>();
        userData.put("userObject",user);

        String jwtToken = Jwts.builder().setIssuer("myApp")
                .setClaims(userData)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 20*60000))
                .signWith(SignatureAlgorithm.HS512,"secretkey")
                .compact();

        map.put("token",jwtToken);
        map.put("Message","successfully logging");
        return map;
    }
}
