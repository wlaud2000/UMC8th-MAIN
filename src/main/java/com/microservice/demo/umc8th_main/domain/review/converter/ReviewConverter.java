package com.microservice.demo.umc8th_main.domain.review.converter;

import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import com.microservice.demo.umc8th_main.domain.review.dto.response.ReviewResDTO;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import com.microservice.demo.umc8th_main.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    // 내 리뷰 목록을 위한 메서드 추가
    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .restaurantName(review.getRestaurant().getName())
                .visitDate(review.getVisitDate())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewListDTO toMyReviewListDTO(Page<Review> reviewPage) {
        List<ReviewResDTO.MyReviewDTO> myReviewDTOList = reviewPage.getContent().stream()
                .map(ReviewConverter::toMyReviewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.MyReviewListDTO.builder()
                .reviewList(myReviewDTOList)
                .listSize(myReviewDTOList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}
