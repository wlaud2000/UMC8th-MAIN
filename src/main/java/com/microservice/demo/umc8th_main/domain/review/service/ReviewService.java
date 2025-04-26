package com.microservice.demo.umc8th_main.domain.review.service;

import com.microservice.demo.umc8th_main.domain.review.dto.request.ReviewReqDTO;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import com.microservice.demo.umc8th_main.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review createReview(ReviewReqDTO.CreateReviewDTO dto) {
        return reviewRepository.createReview(dto);
    }
}
