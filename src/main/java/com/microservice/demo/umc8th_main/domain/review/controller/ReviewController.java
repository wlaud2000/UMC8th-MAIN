package com.microservice.demo.umc8th_main.domain.review.controller;

import com.microservice.demo.umc8th_main.domain.review.converter.ReviewConverter;
import com.microservice.demo.umc8th_main.domain.review.dto.request.ReviewReqDTO;
import com.microservice.demo.umc8th_main.domain.review.dto.response.ReviewResDTO;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import com.microservice.demo.umc8th_main.domain.review.service.ReviewService;
import com.microservice.demo.umc8th_main.global.apiPayload.base.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "리뷰 작성", description = "사용자가 방문한 레스토랑에 대한 리뷰를 작성합니다.")
    public ApiResponse<ReviewResDTO.CreateResultDTO> createReview(
            @Valid @RequestBody ReviewReqDTO.CreateReviewDTO dto
    ) {
        Review review = reviewService.createReview(dto);
        return ApiResponse.onSuccess(ReviewConverter.toCreateResultDTO(review));
    }
}