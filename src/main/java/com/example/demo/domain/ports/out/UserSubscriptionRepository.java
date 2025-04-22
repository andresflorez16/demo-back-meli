package com.example.demo.domain.ports.out;

import com.example.demo.infrastructure.dto.RequestUserSubscriptionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.dto.SubscriptionSummaryDTO;
import com.example.demo.infrastructure.entity.UserSubscriptionEntity;

import java.util.List;
import java.util.Optional;

public interface UserSubscriptionRepository {
    ResponseDTO addUserSubscription(RequestUserSubscriptionDTO requestUserSubscriptionDTO);
    ResponseDTO getSubscriptionsCosts(String email);

    Optional<UserSubscriptionEntity> getInternalUserSubcriptionByUserIdAndSubscriptionId(Long userId, Long subscriptionId);
    List<SubscriptionSummaryDTO> getInternalUserSubcriptionsByUserId(Long userId);
}
