package com.example.demo.domain.ports.out;

import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.entity.SubscriptionEntity;

public interface SubscriptionRepository {
    ResponseDTO saveSubscription(RequestSubscriptionDTO requestSubscriptionDTO);

    SubscriptionEntity findSubscriptionByPartner(String partner);
}
