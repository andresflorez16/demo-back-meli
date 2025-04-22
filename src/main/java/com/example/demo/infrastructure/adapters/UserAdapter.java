package com.example.demo.infrastructure.adapters;

import com.example.demo.domain.ports.out.UserRepository;
import com.example.demo.domain.ports.out.UserSessionRepository;
import com.example.demo.domain.ports.out.UserSubscriptionRepository;
import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.dto.RequestUserDTO;
import com.example.demo.infrastructure.entity.UserEntity;
import com.example.demo.infrastructure.entity.UserSubscriptionEntity;
import com.example.demo.infrastructure.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.mappers.UserMapper;
import com.example.demo.infrastructure.repository.JpaUserRepository;
import com.example.demo.infrastructure.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserAdapter implements UserRepository {

    private final JpaUserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSessionRepository userSessionRepository;

    @Autowired
    public UserAdapter(JpaUserRepository userRepository, UserMapper userMapper, UserSessionRepository userSessionRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userSessionRepository = userSessionRepository;
    }

    @Override
    public ResponseDTO saveUser(RequestUserDTO requestUserDTO) {
        UserEntity userEntity = userMapper.toEntityfromUserDataDto(requestUserDTO);
        userRepository.save(userEntity);
        return new ResponseDTO(Constants.CODE_OK, "User created successfully");
    }

//    @Override PENDING TO MAPPING DTO
//    public ResponseDTO getUserByEmail(String email) {
//        UserEntity userEntity = userRepository.findByEmail(email);
//        return new ResponseDTO(Constants.CODE_OK, "User found", userMapper.toDto(userEntity));
//    }

    @Override
    public UserEntity getInternalUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
