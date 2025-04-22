package com.example.demo.domain.ports.in;

import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.dto.RequestUserDTO;
import com.example.demo.infrastructure.entity.UserEntity;

public interface UserUseCase {
    ResponseDTO saveUser(RequestUserDTO requestUserDTO);
}
