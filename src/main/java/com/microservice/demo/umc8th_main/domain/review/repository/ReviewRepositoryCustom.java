package com.microservice.demo.umc8th_main.domain.review.repository;

import com.microservice.demo.umc8th_main.domain.review.dto.request.ReviewReqDTO;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;

public interface ReviewRepositoryCustom {

    Review createReview(ReviewReqDTO.CreateReviewDTO dto);

    void updateRestaurantRating(Long restaurantId, float newRating);
}
