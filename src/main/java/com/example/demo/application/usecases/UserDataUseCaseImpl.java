package com.example.demo.application.usecases;

import com.example.demo.domain.ports.in.UserUseCase;
import com.example.demo.domain.ports.out.UserRepository;
import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.dto.RequestUserDTO;
import com.example.demo.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataUseCaseImpl implements UserUseCase {
    private final UserRepository userRepository;

    @Autowired
    public UserDataUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDTO saveUser(RequestUserDTO requestUserDTO) {
        return userRepository.saveUser(requestUserDTO);
    }
}
