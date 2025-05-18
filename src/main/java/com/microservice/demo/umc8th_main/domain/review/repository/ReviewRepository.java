package com.microservice.demo.umc8th_main.domain.review.repository;

import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    // 기본 JPA 메서드는 자동으로 제공됨
    Page<Review> findAllByUserId(Long userId, Pageable pageable);
}
