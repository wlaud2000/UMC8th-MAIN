package com.microservice.demo.umc8th_main.domain.review.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record CreateResultDTO(
            Long id,
            String content,
            Float rating,
            String restaurantName,
            LocalDate visitDate,
            LocalDateTime createdAt
    ) {}
}
