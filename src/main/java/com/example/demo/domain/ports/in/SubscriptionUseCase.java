package com.example.demo.domain.ports.in;

import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;

public interface SubscriptionUseCase {
    ResponseDTO saveSubscription(RequestSubscriptionDTO requestSubscriptionDTO);
}
