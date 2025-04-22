package com.example.demo.infrastructure.mappers;

import com.example.demo.infrastructure.dto.RequestSubscriptionDTO;
import com.example.demo.infrastructure.entity.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    @Mappings({
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    SubscriptionEntity toEntityfromSubscriptionDataDto(RequestSubscriptionDTO requestSubscriptionDTO);
}
