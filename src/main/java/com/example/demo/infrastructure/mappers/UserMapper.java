package com.example.demo.infrastructure.mappers;

import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.dto.RequestUserDTO;
import com.example.demo.infrastructure.entity.SubscriptionEntity;
import com.example.demo.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    UserEntity toEntityfromUserDataDto(RequestUserDTO requestUserDTO);
}
