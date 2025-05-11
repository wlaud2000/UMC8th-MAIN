package com.microservice.demo.umc8th_main.domain.restaurant.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class RestaurantResDTO {

    @Builder
    public record CreateResultDTO(
            Long id,
            String name,
            String address,
            String category,
            LocalDateTime createdAt
    ) {}
}
