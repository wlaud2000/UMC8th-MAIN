package com.microservice.demo.umc8th_main.domain.mission.repository;

import com.microservice.demo.umc8th_main.domain.mission.entity.Mission;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
    // 기본 JPA 메서드는 자동으로 제공됨
    // 가게별 미션 조회 메서드 추가
    Page<Mission> findAllByRestaurant(Restaurant restaurant, Pageable pageable);
}
