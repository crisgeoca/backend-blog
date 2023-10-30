package com.solvedex.blog.controller.usercontroller;

import com.solvedex.blog.dto.CreateUserDto;
import com.solvedex.blog.service.interfaces.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/signup")
@RequiredArgsConstructor
public class UserController {

    private final UserInterface userInterface;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
        userInterface.createUser(createUserDto);
        return ResponseEntity.ok().build();
    }

}
