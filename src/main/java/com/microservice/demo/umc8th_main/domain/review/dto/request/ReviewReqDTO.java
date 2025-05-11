package com.microservice.demo.umc8th_main.domain.review.dto.request;

import com.microservice.demo.umc8th_main.global.validation.annotation.ExistRestaurant;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ReviewReqDTO {

    public record CreateReviewDTO(
            // 로그인 기능 없이 하드코딩 예정이므로 검증 생략
            Long userId,

            @NotNull(message = "레스토랑 ID는 필수입니다")
            @ExistRestaurant
            Long restaurantId,

            @NotNull(message = "평점은 필수입니다")
            @Min(value = 1, message = "평점은 최소 1점 이상이어야 합니다")
            @Max(value = 5, message = "평점은 최대 5점까지 가능합니다")
            Float rating,

            @NotBlank(message = "리뷰 내용은 필수입니다")
            String content,

            LocalDate visitDate
    ) {
    }
}
