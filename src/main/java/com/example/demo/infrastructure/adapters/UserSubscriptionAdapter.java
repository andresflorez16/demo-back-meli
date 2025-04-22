package com.example.demo.infrastructure.adapters;

import com.example.demo.domain.ports.out.*;
import com.example.demo.infrastructure.dto.*;
import com.example.demo.infrastructure.entity.SubscriptionEntity;
import com.example.demo.infrastructure.entity.UserEntity;
import com.example.demo.infrastructure.entity.UserSubscriptionEntity;
import com.example.demo.infrastructure.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.exceptions.UnprocessableEntityException;
import com.example.demo.infrastructure.mappers.UserMapper;
import com.example.demo.infrastructure.mappers.UserSubscriptionMapper;
import com.example.demo.infrastructure.repository.JpaUserRepository;
import com.example.demo.infrastructure.repository.JpaUserSubscriptionRepository;
import com.example.demo.infrastructure.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserSubscriptionAdapter implements UserSubscriptionRepository {

    private final JpaUserSubscriptionRepository userRepository;
    private final UserSubscriptionMapper userMapper;
    private final UserRepository userRepositoryPort;
    private final SubscriptionRepository subscriptionRepositoryPort;
    private final UserFriendShipRepository userFriendShipRepositoryPort;
    private final UserSessionRepository userSessionRepositoryPort;

    @Autowired
    public UserSubscriptionAdapter(JpaUserSubscriptionRepository userRepository, UserSubscriptionMapper userMapper, UserRepository userRepositoryPort, SubscriptionRepository subscriptionRepositoryPort, UserFriendShipRepository userFriendShipRepositoryPort, UserSessionRepository userSessionRepositoryPort) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userRepositoryPort = userRepositoryPort;
        this.subscriptionRepositoryPort = subscriptionRepositoryPort;
        this.userFriendShipRepositoryPort = userFriendShipRepositoryPort;
        this.userSessionRepositoryPort = userSessionRepositoryPort;
    }

    @Override
    public ResponseDTO addUserSubscription(RequestUserSubscriptionDTO requestUserSubscriptionDTO) throws ResourceNotFoundException, DataIntegrityViolationException {
        UserEntity userEntity = userRepositoryPort.getInternalUserByEmail(requestUserSubscriptionDTO.getEmail());
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found");
        }

        SubscriptionEntity subscriptionEntity = subscriptionRepositoryPort.findSubscriptionByPartner(requestUserSubscriptionDTO.getPartner());
        if (subscriptionEntity == null) {
            throw new ResourceNotFoundException("Subscription not found");
        }

        log.info("--- UserEntity: {} ---", userEntity);
        log.info("--- SubscriptionEntity: {} ---", subscriptionEntity);


        Optional<UserSubscriptionEntity> hasUserSubscription = getInternalUserSubcriptionByUserIdAndSubscriptionId(userEntity.getId(), subscriptionEntity.getId());
        log.info("--- hasUserSubscription?: {} ---", hasUserSubscription);

        if (hasUserSubscription.isPresent()) {
            throw new DataIntegrityViolationException("User Subscription already exists");
        }

        UserSubscriptionDTO userSubscriptionDTO = userMapper.toDTO(userEntity, subscriptionEntity);
        log.info("--- UserSubscriptionDTO: {} ---", userSubscriptionDTO);

        UserSubscriptionEntity userSubscriptionEntity = userMapper.toEntity(userSubscriptionDTO);
        log.info("--- UserSubscriptionEntity: {} ---", userSubscriptionEntity);


        userRepository.save(userSubscriptionEntity);
        return new ResponseDTO(Constants.CODE_OK, "Subscription linked successfully");
    }

    @Override
    public Optional<UserSubscriptionEntity> getInternalUserSubcriptionByUserIdAndSubscriptionId(Long userId, Long subscriptionId) {
        UserSubscriptionEntity userSubscriptionEntity = userRepository.findByUserIdAndSubscriptionId(userId, subscriptionId);
        if (userSubscriptionEntity == null) {
            return Optional.empty();
        }
        return Optional.of(userSubscriptionEntity);
    }

    @Override
    public List<SubscriptionSummaryDTO> getInternalUserSubcriptionsByUserId(Long userId) {
        List<SubscriptionSummaryDTO> userSubscriptionsEntity = userRepository.findSubscriptionsByUserId(userId);
        if (userSubscriptionsEntity == null || userSubscriptionsEntity.isEmpty()) {
            return List.of();
        }
        return userSubscriptionsEntity;
    }

    @Override
    public ResponseDTO getSubscriptionsCosts(String email) throws ResourceNotFoundException, IllegalStateException, UnprocessableEntityException {
        UserEntity loggedUser = userSessionRepositoryPort.getLoggedUser();
        UserEntity userEntity = userRepositoryPort.getInternalUserByEmail(email);
        log.info("User found: {}", userEntity);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found");
        }

        boolean isFrienshipExists = userFriendShipRepositoryPort.isFirendshipExists(loggedUser.getId(), userEntity.getId());
        log.info("Is friendship exists: {}", isFrienshipExists);
        if (!isFrienshipExists) {
            throw new UnprocessableEntityException("User is not a friend");
        }

        List<SubscriptionSummaryDTO> userSubscriptions = getInternalUserSubcriptionsByUserId(userEntity.getId());
        log.info("User subscriptions: {}", userSubscriptions);
        if (userSubscriptions == null || userSubscriptions.isEmpty()) {
            return new ResponseDTO(Constants.CODE_OK, "User has no subscriptions", null);
        }

        Float totalCost = userSubscriptions.stream()
                .map(SubscriptionSummaryDTO::getPrice)
                .reduce(0f, Float::sum);

        log.info("Total cost: {}", totalCost);

        String message = String.format("Total cost: $%.2f", totalCost);
        return new ResponseDTO(Constants.CODE_OK, message, totalCost);
    }

}
