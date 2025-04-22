package com.example.demo.infrastructure.repository;

import com.example.demo.infrastructure.dto.SubscriptionSummaryDTO;
import com.example.demo.infrastructure.entity.SubscriptionEntity;
import com.example.demo.infrastructure.entity.UserSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserSubscriptionRepository extends JpaRepository<UserSubscriptionEntity, Integer> {
    UserSubscriptionEntity findByUserIdAndSubscriptionId(Long userId, Long subscriptionId);


    @Query("SELECT new com.example.demo.infrastructure.dto.SubscriptionSummaryDTO(s.partner, s.price) FROM UserSubscriptionEntity us JOIN us.subscription s WHERE us.user.id = :userId")
    List<SubscriptionSummaryDTO> findSubscriptionsByUserId(@Param("userId") Long userId);
}
