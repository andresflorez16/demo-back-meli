package com.example.demo.application.usecases;

import com.example.demo.domain.ports.in.SubscriptionUseCase;
import com.example.demo.domain.ports.out.SubscriptionRepository;
import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionDataUseCaseImpl implements SubscriptionUseCase {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Override
    public ResponseDTO saveSubscription(RequestSubscriptionDTO requestSubscriptionDTO) {
        return subscriptionRepository.saveSubscription(requestSubscriptionDTO);
    }
}
