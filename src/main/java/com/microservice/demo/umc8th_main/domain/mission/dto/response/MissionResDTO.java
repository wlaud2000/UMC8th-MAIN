package com.microservice.demo.umc8th_main.domain.mission.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    public record CreateResultDTO(
            Long id,
            String title,
            String restaurantName,
            int rewardPoints,
            LocalDate startDate,
            LocalDate endDate,
            LocalDateTime createdAt
    ) {}
}
