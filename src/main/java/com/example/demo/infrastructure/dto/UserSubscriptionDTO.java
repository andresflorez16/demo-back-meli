package com.example.demo.infrastructure.dto;

import com.example.demo.infrastructure.entity.SubscriptionEntity;
import com.example.demo.infrastructure.entity.UserEntity;
import com.example.demo.infrastructure.utils.Constants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSubscriptionDTO {
    @NotNull(message = "User must not be null")
    @Valid
    private UserEntity user;

    @NotNull(message = "Subscription must not be null")
    @Valid
    private SubscriptionEntity subscription;
}
