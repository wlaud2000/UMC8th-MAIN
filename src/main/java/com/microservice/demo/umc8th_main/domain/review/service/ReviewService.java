package com.microservice.demo.umc8th_main.domain.review.service;

import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import com.microservice.demo.umc8th_main.domain.restaurant.repository.RestaurantRepository;
import com.microservice.demo.umc8th_main.domain.review.converter.ReviewConverter;
import com.microservice.demo.umc8th_main.domain.review.dto.request.ReviewReqDTO;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import com.microservice.demo.umc8th_main.domain.review.repository.ReviewRepository;
import com.microservice.demo.umc8th_main.domain.user.entity.User;
import com.microservice.demo.umc8th_main.domain.user.repository.UserRepository;
import com.microservice.demo.umc8th_main.global.apiPayload.code.ErrorStatus;
import com.microservice.demo.umc8th_main.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Review createReview(ReviewReqDTO.CreateReviewDTO dto) {
        // 1. 사용자 조회 (하드코딩)
        User user = userRepository.findById(1L)  // 하드코딩된 사용자 ID
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 2. 레스토랑 조회 (커스텀 어노테이션으로 이미 검증됨)
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        // 3. 리뷰 생성
        Review review = ReviewConverter.toReview(
                user,
                restaurant,
                dto.rating(),
                dto.content(),
                dto.visitDate()
        );

        // 4. 리뷰 저장
        Review savedReview = reviewRepository.save(review);

        // 5. 레스토랑 평점 업데이트
        updateRestaurantRating(restaurant, dto.rating());

        return savedReview;
    }

    private void updateRestaurantRating(Restaurant restaurant, float newRating) {
        // 평점 업데이트 로직
        float currentRating = restaurant.getRating();
        int reviewCount = restaurant.getReviewCount();

        float newAverageRating;
        if (reviewCount == 0) {
            newAverageRating = newRating;
        } else {
            newAverageRating = (currentRating * reviewCount + newRating) / (reviewCount + 1);
        }

        // Restaurant 엔티티를 직접 업데이트할 수 없으므로 DTO를 통해 업데이트
        // 실제 구현 시 Restaurant 엔티티에 update 메서드 추가 필요
    }
}