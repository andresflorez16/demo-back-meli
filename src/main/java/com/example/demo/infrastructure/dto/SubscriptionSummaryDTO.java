package com.example.demo.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class SubscriptionSummaryDTO {
    private String partner;
    private Float price;

    public SubscriptionSummaryDTO(String partner, Float price) {
        this.partner = partner;
        this.price = price;
    }
}
