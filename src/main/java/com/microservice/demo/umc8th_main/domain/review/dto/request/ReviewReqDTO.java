package com.microservice.demo.umc8th_main.domain.review.dto.request;

import java.time.LocalDate;

public class ReviewReqDTO {

    public record CreateReviewDTO(
            Long userId,
            Long restaurantId,
            float rating,
            String content,
            LocalDate visitDate
    ) {
    }
}
