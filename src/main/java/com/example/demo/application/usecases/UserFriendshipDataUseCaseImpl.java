package com.example.demo.application.usecases;

import com.example.demo.domain.ports.in.UserFriendshipUseCase;
import com.example.demo.domain.ports.in.UserUseCase;
import com.example.demo.domain.ports.out.UserFriendShipRepository;
import com.example.demo.domain.ports.out.UserRepository;
import com.example.demo.infrastructure.dto.RequestUserDTO;
import com.example.demo.infrastructure.dto.RequestUserFriendshipDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFriendshipDataUseCaseImpl implements UserFriendshipUseCase {
    private final UserFriendShipRepository userFriendShipRepository;

    @Autowired
    public UserFriendshipDataUseCaseImpl(UserFriendShipRepository userFriendShipRepository) {
        this.userFriendShipRepository = userFriendShipRepository;
    }

    @Override
    public ResponseDTO addFriend(RequestUserFriendshipDTO requestUserFriendshipDTO) {
        return userFriendShipRepository.addFriend(requestUserFriendshipDTO);
    }
}
