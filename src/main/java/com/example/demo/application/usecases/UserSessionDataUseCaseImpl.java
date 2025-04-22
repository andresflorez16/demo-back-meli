package com.example.demo.application.usecases;

import com.example.demo.domain.ports.in.UserSessionUseCase;
import com.example.demo.domain.ports.out.UserSessionRepository;
import com.example.demo.infrastructure.dto.RequestUserSessionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSessionDataUseCaseImpl implements UserSessionUseCase {
    private final UserSessionRepository userSessionRepository;

    @Autowired
    public UserSessionDataUseCaseImpl(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    @Override
    public ResponseDTO setLoggedUser(RequestUserSessionDTO requestUserSessionDTO) {
        return userSessionRepository.setLoggedUser(requestUserSessionDTO);
    }
}
