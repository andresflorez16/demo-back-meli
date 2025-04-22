package com.example.demo.infrastructure.mappers;

import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.entity.SubscriptionEntity;
import com.example.demo.infrastructure.entity.UserEntity;
import com.example.demo.infrastructure.entity.UserFriendshipEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserFriendshipMapper {
    @Mappings({
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    UserFriendshipEntity toEntity(UserEntity user, UserEntity friend);
}
