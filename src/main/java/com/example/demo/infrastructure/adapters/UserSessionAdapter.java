package com.example.demo.infrastructure.adapters;

import com.example.demo.domain.ports.out.SubscriptionRepository;
import com.example.demo.domain.ports.out.UserRepository;
import com.example.demo.domain.ports.out.UserSessionRepository;
import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.dto.RequestUserSessionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.entity.SubscriptionEntity;
import com.example.demo.infrastructure.entity.UserEntity;
import com.example.demo.infrastructure.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.mappers.SubscriptionMapper;
import com.example.demo.infrastructure.repository.JpaSubscriptionRepository;
import com.example.demo.infrastructure.repository.JpaUserRepository;
import com.example.demo.infrastructure.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Slf4j
public class UserSessionAdapter implements UserSessionRepository {

    private UserEntity loggedUser;
    private final UserRepository userRepository;

    @Autowired
    public UserSessionAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDTO setLoggedUser(RequestUserSessionDTO requestUserSessionDTO) throws ResourceNotFoundException {
        this.loggedUser = userRepository.getInternalUserByEmail(requestUserSessionDTO.getEmail());
        if (loggedUser == null) {
            throw new ResourceNotFoundException("User not found");
        }

        return new ResponseDTO(Constants.CODE_OK, "User logged in successfully", loggedUser);
    }

    @Override
    public UserEntity getLoggedUser() throws IllegalStateException {
        if (loggedUser == null) {
            throw new IllegalStateException("No user is logged in");
        }
        return this.loggedUser;
    }

}
