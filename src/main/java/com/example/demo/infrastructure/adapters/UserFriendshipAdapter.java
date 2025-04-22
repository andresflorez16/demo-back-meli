package com.example.demo.infrastructure.adapters;

import com.example.demo.domain.ports.out.SubscriptionRepository;
import com.example.demo.domain.ports.out.UserFriendShipRepository;
import com.example.demo.domain.ports.out.UserRepository;
import com.example.demo.domain.ports.out.UserSessionRepository;
import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.dto.RequestUserFriendshipDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.entity.SubscriptionEntity;
import com.example.demo.infrastructure.entity.UserEntity;
import com.example.demo.infrastructure.entity.UserFriendshipEntity;
import com.example.demo.infrastructure.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.mappers.SubscriptionMapper;
import com.example.demo.infrastructure.mappers.UserFriendshipMapper;
import com.example.demo.infrastructure.repository.JpaSubscriptionRepository;
import com.example.demo.infrastructure.repository.JpaUserFriendshipRepository;
import com.example.demo.infrastructure.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserFriendshipAdapter implements UserFriendShipRepository {

    private final JpaUserFriendshipRepository jpaUserFriendshipRepository;
    private final UserSessionRepository userSessionRepository;
    private final UserRepository userRepository;
    private final UserFriendshipMapper userFriendshipMapper;

    @Autowired
    public UserFriendshipAdapter(JpaUserFriendshipRepository jpaUserFriendshipRepository, UserSessionRepository userSessionRepository, UserRepository userRepository, UserFriendshipMapper userFriendshipMapper) {
        this.jpaUserFriendshipRepository = jpaUserFriendshipRepository;
        this.userSessionRepository = userSessionRepository;
        this.userRepository = userRepository;
        this.userFriendshipMapper = userFriendshipMapper;
    }

    @Override
    public ResponseDTO addFriend(RequestUserFriendshipDTO requestUserFriendshipDTO) throws ResourceNotFoundException, IllegalStateException, DataIntegrityViolationException {
        UserEntity loggedUser = userSessionRepository.getLoggedUser();

        UserEntity friendUser = userRepository.getInternalUserByEmail(requestUserFriendshipDTO.getEmail());
        if (friendUser == null) {
            throw new ResourceNotFoundException("Friend not found");
        }

        if (Objects.equals(loggedUser.getId(), friendUser.getId())) {
            throw new DataIntegrityViolationException("Cannot add yourself as a friend");
        }

        boolean isFriend = jpaUserFriendshipRepository.existsFriendshipBidirectional(loggedUser.getId(), friendUser.getId());
        if (isFriend) {
            return new ResponseDTO(Constants.CODE_OK, "Friend already exists");
        }

        UserFriendshipEntity userFriendshipEntity = userFriendshipMapper.toEntity(loggedUser, friendUser);

        jpaUserFriendshipRepository.save(userFriendshipEntity);
        return new ResponseDTO(Constants.CODE_OK, "Friend added successfully");
    }

    @Override
    public boolean isFirendshipExists(Long userId, Long friendId) {
        return jpaUserFriendshipRepository.existsFriendshipBidirectional(userId, friendId);
    }

}
