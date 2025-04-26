package com.microservice.demo.umc8th_main.domain.review.repository;

import com.microservice.demo.umc8th_main.domain.restaurant.entity.QRestaurant;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import com.microservice.demo.umc8th_main.domain.review.dto.request.ReviewReqDTO;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import com.microservice.demo.umc8th_main.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Review createReview(ReviewReqDTO.CreateReviewDTO dto) {
        // 1. 사용자와 레스토랑 조회
        User user = entityManager.find(User.class, dto.userId());
        if (user == null) {
            throw new EntityNotFoundException("User not found with id: " + dto.userId());
        }

        Restaurant restaurant = entityManager.find(Restaurant.class, dto.restaurantId());
        if (restaurant == null) {
            throw new EntityNotFoundException("Restaurant not found with id: " + dto.restaurantId());
        }

        // 2. 리뷰 엔티티 생성
        Review review = Review.builder()
                .user(user)
                .restaurant(restaurant)
                .rating(dto.rating())
                .content(dto.content())
                .visitDate(dto.visitDate() != null ? dto.visitDate() : LocalDate.now())
                .likeCount(0)
                .build();

        // 3. 리뷰 저장
        entityManager.persist(review);

        // 4. 레스토랑 평점 업데이트
        updateRestaurantRating(restaurant.getId(), dto.rating());

        return review;
    }

    @Override
    @Transactional
    public void updateRestaurantRating(Long restaurantId, float newRating) {
        QRestaurant qRestaurant = QRestaurant.restaurant;

        // 레스토랑 조회
        Restaurant restaurant = queryFactory
                .selectFrom(qRestaurant)
                .where(qRestaurant.id.eq(restaurantId))
                .fetchOne();

        if (restaurant == null) {
            throw new EntityNotFoundException("Restaurant not found with id: " + restaurantId);
        }

        // 새로운 평점 계산
        float currentRating = restaurant.getRating();
        int reviewCount = restaurant.getReviewCount();

        // 처음 리뷰인 경우
        if (reviewCount == 0) {
            // QueryDSL update 구문 사용
            queryFactory.update(qRestaurant)
                    .set(qRestaurant.rating, newRating)
                    .set(qRestaurant.reviewCount, 1)
                    .where(qRestaurant.id.eq(restaurantId))
                    .execute();
        } else {
            // 새로운 평균 평점 계산: (기존 평점 * 리뷰 수 + 새 평점) / (리뷰 수 + 1)
            float newAverageRating = (currentRating * reviewCount + newRating) / (reviewCount + 1);

            // QueryDSL update 구문 사용
            queryFactory.update(qRestaurant)
                    .set(qRestaurant.rating, newAverageRating)
                    .set(qRestaurant.reviewCount, reviewCount + 1)
                    .where(qRestaurant.id.eq(restaurantId))
                    .execute();
        }

        // 영속성 컨텍스트 초기화 (update 구문은 영속성 컨텍스트를 무시하므로)
        entityManager.flush();
        entityManager.clear();
    }
}
