package com.microservice.demo.umc8th_main.domain.review.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SliceReviewResDTO {

    @Builder
    public record MyReviewSliceDTO(
            Long id,
            String content,
            Float rating,
            String restaurantName,
            LocalDate visitDate,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record MyReviewSliceListDTO(
            List<MyReviewSliceDTO> reviewList,
            Integer listSize,
            Boolean hasNext,
            Boolean isFirst,
            Boolean isLast
    ) {}
}