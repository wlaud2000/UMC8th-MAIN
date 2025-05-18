package com.microservice.demo.umc8th_main.domain.mission.repository;

import com.microservice.demo.umc8th_main.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long>, UserMissionRepositoryCustom {
    // 기존 JPA 메서드는 자동으로 제공됨
    boolean existsByUserIdAndMissionIdAndStatusIn(Long userId, Long missionId, List<String> statuses);

    // 사용자별 미션 조회 메서드 추가
    Page<UserMission> findAllByUserIdAndStatus(Long userId, String status, Pageable pageable);
    Page<UserMission> findAllByUserId(Long userId, Pageable pageable);
}
