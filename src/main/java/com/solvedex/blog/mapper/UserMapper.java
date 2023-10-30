package com.solvedex.blog.mapper;

import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.CreateUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "username", source = "createUserDto.username")
    User toUser(CreateUserDto createUserDto);

}
