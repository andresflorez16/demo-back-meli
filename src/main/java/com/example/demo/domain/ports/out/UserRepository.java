package com.example.demo.domain.ports.out;

import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.dto.RequestUserDTO;
import com.example.demo.infrastructure.entity.UserEntity;

public interface UserRepository {
    ResponseDTO saveUser(RequestUserDTO requestUserDTO);
    UserEntity getInternalUserByEmail(String email);
}
