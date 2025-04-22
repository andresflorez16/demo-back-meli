package com.example.demo.application.usecases;

import com.example.demo.domain.ports.in.UserSubscriptionUseCase;
import com.example.demo.domain.ports.in.UserUseCase;
import com.example.demo.domain.ports.out.UserRepository;
import com.example.demo.domain.ports.out.UserSubscriptionRepository;
import com.example.demo.infrastructure.dto.RequestUserDTO;
import com.example.demo.infrastructure.dto.RequestUserSubscriptionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSubscriptionDataUseCaseImpl implements UserSubscriptionUseCase {
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Autowired
    public UserSubscriptionDataUseCaseImpl(UserSubscriptionRepository userSubscriptionRepository) {
        this.userSubscriptionRepository = userSubscriptionRepository;
    }

    @Override
    public ResponseDTO addUserSubscription(RequestUserSubscriptionDTO requestUserSubscriptionDTO) {
        return userSubscriptionRepository.addUserSubscription(requestUserSubscriptionDTO);
    }

    @Override
    public ResponseDTO getSubscriptionsCosts(String email) {
        return userSubscriptionRepository.getSubscriptionsCosts(email);
    }
}
