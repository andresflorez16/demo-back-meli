package com.example.demo.domain.ports.in;

import com.example.demo.infrastructure.dto.RequestUserDTO;
import com.example.demo.infrastructure.dto.RequestUserSubscriptionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;

public interface UserSubscriptionUseCase {
    ResponseDTO addUserSubscription(RequestUserSubscriptionDTO requestUserSubscriptionDTO);
    ResponseDTO getSubscriptionsCosts(String email);
}
