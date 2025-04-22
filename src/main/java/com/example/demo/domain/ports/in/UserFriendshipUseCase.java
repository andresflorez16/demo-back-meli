package com.example.demo.domain.ports.in;

import com.example.demo.infrastructure.dto.RequestUserFriendshipDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;

public interface UserFriendshipUseCase {
    ResponseDTO addFriend(RequestUserFriendshipDTO requestUserFriendshipDTO);
}
