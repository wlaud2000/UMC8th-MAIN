package com.microservice.demo.umc8th_main.domain.review.converter;

import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import com.microservice.demo.umc8th_main.domain.review.dto.response.ReviewResDTO;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import com.microservice.demo.umc8th_main.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {

    public static Review toReview(User user, Restaurant restaurant, float rating, String content, LocalDate visitDate) {
        return Review.builder()
                .user(user)
                .restaurant(restaurant)
                .rating(rating)
                .content(content)
                .visitDate(visitDate != null ? visitDate : LocalDate.now())
                .likeCount(0)
                .build();
    }

    public static ReviewResDTO.CreateResultDTO toCreateResultDTO(Review review) {
        return ReviewResDTO.CreateResultDTO.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .restaurantName(review.getRestaurant().getName())
                .visitDate(review.getVisitDate())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
