package com.solvedex.blog.service.interfaces;

import com.solvedex.blog.domain.User;

public interface AuthInterface {

    String getToken(User user);

    User getUser(String userName, String password);

    boolean validateTokenExpiration(String token, User user);

}
