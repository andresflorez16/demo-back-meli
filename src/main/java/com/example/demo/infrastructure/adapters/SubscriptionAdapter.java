package com.example.demo.infrastructure.adapters;

import com.example.demo.domain.ports.out.SubscriptionRepository;
import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.entity.SubscriptionEntity;
import com.example.demo.infrastructure.exceptions.SubscriptionTypeValidationException;
import com.example.demo.infrastructure.mappers.SubscriptionMapper;
import com.example.demo.infrastructure.repository.JpaSubscriptionRepository;
import com.example.demo.infrastructure.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SubscriptionAdapter implements SubscriptionRepository {

    private final JpaSubscriptionRepository jpaSubscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionAdapter(JpaSubscriptionRepository jpaSubscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.jpaSubscriptionRepository = jpaSubscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public ResponseDTO saveSubscription(RequestSubscriptionDTO requestSubscriptionDTO) {
            SubscriptionEntity subscription = subscriptionMapper.toEntityfromSubscriptionDataDto(requestSubscriptionDTO);
            jpaSubscriptionRepository.save(subscription);
            return new ResponseDTO(Constants.CODE_OK, "Subscription created successfully");
    }

    @Override
    public SubscriptionEntity findSubscriptionByPartner(String partner) {
        return jpaSubscriptionRepository.findByPartner(partner);
    }

}
