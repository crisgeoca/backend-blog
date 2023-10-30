package com.solvedex.blog.dto;

import lombok.Data;

@Data
public class CreateUserDto {

    private String name;

    private String lastName;

    private String username;

    private String password;

}
