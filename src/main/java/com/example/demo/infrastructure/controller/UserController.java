package com.example.demo.infrastructure.controller;

import com.example.demo.domain.ports.in.UserFriendshipUseCase;
import com.example.demo.domain.ports.in.UserSessionUseCase;
import com.example.demo.domain.ports.in.UserSubscriptionUseCase;
import com.example.demo.domain.ports.in.UserUseCase;
import com.example.demo.domain.ports.out.UserSessionRepository;
import com.example.demo.infrastructure.dto.*;
import com.example.demo.infrastructure.entity.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserUseCase userUseCase;
    private final UserSubscriptionUseCase userSubscriptionUseCase;
    private final UserSessionUseCase userSessionUseCase;
    private final UserFriendshipUseCase userFriendshipUseCase;
    private final UserSessionRepository userSessionRepository;

    @Autowired
    public UserController(UserUseCase userUseCase, UserSubscriptionUseCase userSubscriptionUseCase, UserSessionUseCase userSessionUseCase, UserSessionRepository userSessionRepository, UserFriendshipUseCase userFriendshipUseCase) {
        this.userUseCase = userUseCase;
        this.userSubscriptionUseCase = userSubscriptionUseCase;
        this.userSessionUseCase = userSessionUseCase;
        this.userSessionRepository = userSessionRepository;
        this.userFriendshipUseCase = userFriendshipUseCase;
    }

    @PostMapping("/")
    public ResponseDTO saveUserData(@RequestBody @Valid RequestUserDTO requestUserDTO){
        log.info("--- Start path /subs data: {} ---", requestUserDTO);
        ResponseDTO responseDTO = userUseCase.saveUser(requestUserDTO);
        log.info("--- Finish path /subs data: {} ---", responseDTO);
        return responseDTO;
    }

    @PostMapping("/subscription")
    public ResponseDTO addUserSubscription(@RequestBody @Valid RequestUserSubscriptionDTO requestUserSubscriptionDTO){
        log.info("--- Start path /subs data: {} ---", requestUserSubscriptionDTO);
        ResponseDTO responseDTO = userSubscriptionUseCase.addUserSubscription(requestUserSubscriptionDTO);
        log.info("--- Finish path /subs data: {} ---", responseDTO);
        return responseDTO;
    }

    @PostMapping("/login")
    public ResponseDTO loginUser(@RequestBody @Valid RequestUserSessionDTO requestUserSessionDTO){
        log.info("--- Start path /subs data: {} ---", requestUserSessionDTO);
        ResponseDTO responseDTO = userSessionUseCase.setLoggedUser(requestUserSessionDTO);
        log.info("--- Finish path /subs data: {} ---", responseDTO);
        return responseDTO;
    }

    @PostMapping("/friendship")
    public ResponseDTO addFriend(@RequestBody @Valid RequestUserFriendshipDTO requestUserFriendshipDTO){
        log.info("--- Start path /subs data: {} ---", requestUserFriendshipDTO);
        ResponseDTO responseDTO = userFriendshipUseCase.addFriend(requestUserFriendshipDTO);
        log.info("--- Finish path /subs data: {} ---", responseDTO);
        return responseDTO;
    }

    @GetMapping("/costs")
    public ResponseDTO getUserSubscriptionsCost(
            @RequestParam @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email
        ){
        log.info("--- Start path /subs data: {} ---", email);

        UserEntity user = userSessionRepository.getLoggedUser();
        log.info("--- User logged: {} ---", user);
        ResponseDTO responseDTO = userSubscriptionUseCase.getSubscriptionsCosts(email);

        log.info("--- Finish path /subs data: {} ---", responseDTO);
        return responseDTO;
    }
}
