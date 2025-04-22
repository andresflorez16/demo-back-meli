package com.example.demo.domain.ports.out;

import com.example.demo.infrastructure.dto.RequestUserSessionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.entity.UserEntity;

public interface UserSessionRepository {
    ResponseDTO setLoggedUser(RequestUserSessionDTO requestUserSessionDTO);
    UserEntity getLoggedUser();
}
