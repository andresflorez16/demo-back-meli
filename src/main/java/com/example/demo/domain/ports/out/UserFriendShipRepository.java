package com.example.demo.domain.ports.out;

import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.dto.RequestUserFriendshipDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.entity.SubscriptionEntity;

public interface UserFriendShipRepository {
    ResponseDTO addFriend(RequestUserFriendshipDTO requestUserFriendshipDTO);

    boolean isFirendshipExists(Long userId, Long friendId);
}
