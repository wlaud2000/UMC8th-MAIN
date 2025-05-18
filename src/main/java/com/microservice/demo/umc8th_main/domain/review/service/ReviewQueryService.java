package com.microservice.demo.umc8th_main.domain.review.service;

import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import com.microservice.demo.umc8th_main.domain.review.repository.ReviewRepository;
import com.microservice.demo.umc8th_main.global.validation.validator.PageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public Page<Review> getMyReviews(Long userId, Integer page) {
        // 페이지 번호를 0-based index로 변환
        int pageIndex = PageValidator.convertToPageIndex(page);
        return reviewRepository.findAllByUserId(userId, PageRequest.of(pageIndex, 10));
    }
}