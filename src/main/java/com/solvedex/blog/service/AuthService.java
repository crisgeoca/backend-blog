package com.solvedex.blog.service;

import com.solvedex.blog.domain.User;
import com.solvedex.blog.service.interfaces.AuthInterface;
import com.solvedex.blog.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthInterface {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Override
    public String getToken(User user) {
        return jwtUtil.generateToken(user);
    }

    @Override
    public User getUser(String userName, String password) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        User user = (User) authenticate.getPrincipal();
        user.setPassword(null);
        return user;
    }

    @Override
    public boolean validateTokenExpiration(String token, User user) {
        return jwtUtil.validateToken(token, user);
    }

}
