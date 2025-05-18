package com.microservice.demo.umc8th_main.domain.mission.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    public record StoreMissionDTO(
            Long id,
            String title,
            String description,
            int rewardPoints,
            int minSpendAmount,
            String status,
            LocalDate startDate,
            LocalDate endDate
    ) {}

    @Builder
    public record StoreMissionListDTO(
            List<StoreMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
