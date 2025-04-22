package com.example.demo.domain.ports.in;

import com.example.demo.infrastructure.dto.RequestUserSessionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;

public interface UserSessionUseCase {
    ResponseDTO setLoggedUser(RequestUserSessionDTO requestUserSessionDTO);
}
