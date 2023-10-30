package com.solvedex.blog.service;

import com.solvedex.blog.domain.Authority;
import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.CreateUserDto;
import com.solvedex.blog.mapper.UserMapper;
import com.solvedex.blog.repository.AuthRepository;
import com.solvedex.blog.repository.UserRepository;
import com.solvedex.blog.service.interfaces.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {

    private static final UserMapper MAPPER = UserMapper.INSTANCE;

    private final UserRepository userRepository;

    private final AuthRepository authRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(CreateUserDto createUserDto) {

        User user = MAPPER.toUser(createUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        authRepository.save(Authority.builder().authority("ROLE_ADMIN").user(user).build());

    }

}
