package com.microservice.demo.umc8th_main.domain.mission.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserMissionResDTO {

    @Builder
    public record UserMissionResponseDTO(
            Long id,
            String title,
            String description,
            int rewardPoints,
            String status,
            LocalDateTime startedAt,
            LocalDateTime completedAt,
            String restaurantName,
            int minSpendAmount,
            Integer spentAmount
    ) {
    }

    @Builder
    public record HomeMissionResponseDTO(
            Long id,
            String title,
            String description,
            int rewardPoints,
            String restaurantName,
            String restaurantCategory,
            int minSpendAmount,
            LocalDate endDate,
            int daysRemaining
    ) {
    }

    @Builder
    public record JoinResultDTO(
            Long id,
            Long missionId,
            String status,
            LocalDateTime startedAt
    ) {}

    @Builder
    public record MyMissionDTO(
            Long id,
            String missionTitle,
            String missionDescription,
            int rewardPoints,
            String status,
            LocalDateTime startedAt,
            LocalDateTime completedAt,
            String restaurantName,
            int minSpendAmount,
            Integer spentAmount
    ) {
    }

    @Builder
    public record MyMissionListDTO(
            List<MyMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }
}
