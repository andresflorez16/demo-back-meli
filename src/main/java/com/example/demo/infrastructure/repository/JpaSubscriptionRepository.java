package com.example.demo.infrastructure.repository;

import com.example.demo.infrastructure.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
    SubscriptionEntity findByPartner(String partner);
}
