package com.example.demo.infrastructure.controller;

import com.example.demo.domain.ports.in.SubscriptionUseCase;
import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/subscription")
@Slf4j
public class SubscriptionController {

    private final SubscriptionUseCase subscriptionUseCase;

    @Autowired
    public SubscriptionController(SubscriptionUseCase subscriptionUseCase) {
        this.subscriptionUseCase = subscriptionUseCase;
    }

    @PostMapping("/")
    public ResponseDTO saveSubscriptionData(@RequestBody @Valid RequestSubscriptionDTO requestSubscriptionDTO){
        log.info("--- Start path /subs data: {} ---", requestSubscriptionDTO);
        ResponseDTO responseDTO = subscriptionUseCase.saveSubscription(requestSubscriptionDTO);
        log.info("--- Finish path /subs data: {} ---", responseDTO);
        return responseDTO;
    }
}

