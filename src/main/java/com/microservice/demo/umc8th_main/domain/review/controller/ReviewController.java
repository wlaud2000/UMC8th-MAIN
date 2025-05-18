package com.microservice.demo.umc8th_main.domain.review.controller;

import com.microservice.demo.umc8th_main.domain.review.converter.ReviewConverter;
import com.microservice.demo.umc8th_main.domain.review.dto.request.ReviewReqDTO;
import com.microservice.demo.umc8th_main.domain.review.dto.response.ReviewResDTO;
import com.microservice.demo.umc8th_main.domain.review.dto.response.SliceReviewResDTO;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import com.microservice.demo.umc8th_main.domain.review.service.ReviewQueryService;
import com.microservice.demo.umc8th_main.domain.review.service.ReviewService;
import com.microservice.demo.umc8th_main.global.apiPayload.base.ApiResponse;
import com.microservice.demo.umc8th_main.global.validation.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping
    @Operation(summary = "리뷰 작성", description = "사용자가 방문한 레스토랑에 대한 리뷰를 작성합니다.")
    public ApiResponse<ReviewResDTO.CreateResultDTO> createReview(
            @Valid @RequestBody ReviewReqDTO.CreateReviewDTO dto
    ) {
        Review review = reviewService.createReview(dto);
        return ApiResponse.onSuccess(ReviewConverter.toCreateResultDTO(review));
    }

    @GetMapping("/my")
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "내가 작성한 리뷰 목록을 페이징하여 조회합니다. 페이지 번호는 1부터 시작합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호는 1 이상이어야 합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자 ID", required = true),
            @Parameter(name = "page", description = "페이지 번호(1부터 시작)", required = true)
    })
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam(name = "userId") Long userId,
            @Valid @ValidPage @RequestParam(name = "page") Integer page) {

        Page<Review> reviewPage = reviewQueryService.getMyReviews(userId, page);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewListDTO(reviewPage));
    }

    @GetMapping("/my/slice")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 (Slice)", description = "내가 작성한 리뷰 목록을 Slice로 페이징하여 조회합니다. 페이지 번호는 1부터 시작합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호는 1 이상이어야 합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자 ID", required = true),
            @Parameter(name = "page", description = "페이지 번호(1부터 시작)", required = true)
    })
    public ApiResponse<SliceReviewResDTO.MyReviewSliceListDTO> getMyReviewsWithSlice(
            @RequestParam(name = "userId") Long userId,
            @Valid @ValidPage @RequestParam(name = "page") Integer page) {

        Slice<Review> reviewSlice = reviewQueryService.getMyReviewsWithSlice(userId, page);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewSliceListDTO(reviewSlice));
    }
}