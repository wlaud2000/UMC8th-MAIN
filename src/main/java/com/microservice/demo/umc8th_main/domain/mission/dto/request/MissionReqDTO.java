package com.microservice.demo.umc8th_main.domain.mission.dto.request;

import com.microservice.demo.umc8th_main.global.validation.annotation.ExistRestaurant;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class MissionReqDTO {

    public record CreateMissionDTO(
            @NotNull(message = "레스토랑 ID는 필수입니다")
            @ExistRestaurant
            Long restaurantId,

            @NotBlank(message = "미션 제목은 필수입니다")
            @Size(max = 100, message = "미션 제목은 100자 이내여야 합니다")
            String title,

            @NotBlank(message = "미션 설명은 필수입니다")
            String description,

            @NotNull(message = "보상 포인트는 필수입니다")
            @Min(value = 1, message = "보상 포인트는 최소 1 이상이어야 합니다")
            Integer rewardPoints,

            @NotNull(message = "최소 지출 금액은 필수입니다")
            @Min(value = 0, message = "최소 지출 금액은 0 이상이어야 합니다")
            Integer minSpendAmount,

            @NotNull(message = "시작일은 필수입니다")
            LocalDate startDate,

            @NotNull(message = "종료일은 필수입니다")
            LocalDate endDate
    ) {}
}
