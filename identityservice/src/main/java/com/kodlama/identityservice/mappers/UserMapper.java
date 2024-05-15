package com.kodlama.identityservice.mappers;

import com.kodlama.identityservice.dtos.requests.RegisterRequest;
import com.kodlama.identityservice.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper
{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "firstName", source = "name")
    User userFromRegisterRequest(RegisterRequest request);
}
