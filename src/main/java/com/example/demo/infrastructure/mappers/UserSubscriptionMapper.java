package com.example.demo.infrastructure.mappers;

import com.example.demo.infrastructure.dto.RequestUserDTO;
import com.example.demo.infrastructure.dto.RequestUserSubscriptionDTO;
import com.example.demo.infrastructure.dto.UserSubscriptionDTO;
import com.example.demo.infrastructure.entity.SubscriptionEntity;
import com.example.demo.infrastructure.entity.UserEntity;
import com.example.demo.infrastructure.entity.UserSubscriptionEntity;
import com.example.demo.infrastructure.entity.UserSubscriptionId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserSubscriptionMapper {
    @Mappings({
            @Mapping(target = "subscriptionDate", ignore = true),
            @Mapping(target = "id", source = "userSubscriptionDTO", qualifiedByName = "mapToId")
    })

    UserSubscriptionEntity toEntity(UserSubscriptionDTO userSubscriptionDTO);

    UserSubscriptionDTO toDTO(UserEntity user, SubscriptionEntity subscription);

    @Named("mapToId")
    default UserSubscriptionId mapToId(UserSubscriptionDTO dto) {
        return new UserSubscriptionId(dto.getUser().getId(), dto.getSubscription().getId());
    }
}
