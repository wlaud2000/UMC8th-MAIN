package com.microservice.demo.umc8th_main.domain.mission.repository;

import com.microservice.demo.umc8th_main.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long>, UserMissionRepositoryCustom {
    // 기본 JPA 메서드는 자동으로 제공됨
}
