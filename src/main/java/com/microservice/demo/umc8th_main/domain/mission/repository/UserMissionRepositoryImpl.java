package com.microservice.demo.umc8th_main.domain.mission.repository;

import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.QMission;
import com.microservice.demo.umc8th_main.domain.mission.entity.QUserMission;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.QRestaurant;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserMissionRepositoryImpl implements UserMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserMissionResDTO.UserMissionResponseDTO> findUserMissionsWithPaging(Long userId, String status, Long lastId, int limit) {
        QUserMission userMission = QUserMission.userMission;
        QMission mission = QMission.mission;
        QRestaurant restaurant = QRestaurant.restaurant;

        BooleanBuilder builder = new BooleanBuilder();

        // 사용자 ID 조건
        builder.and(userMission.user.id.eq(userId));

        // 상태 필터링 (제공된 경우)
        if (status != null && !status.isEmpty()) {
            builder.and(userMission.status.eq(status));
        }

        // 커서 기반 페이징
        if (lastId != null) {
            builder.and(userMission.id.lt(lastId));
        }

        return queryFactory
                .select(Projections.constructor(UserMissionResDTO.UserMissionResponseDTO.class,
                        userMission.id,
                        mission.title,
                        mission.description,
                        mission.rewardPoints,
                        userMission.status,
                        userMission.startedAt,
                        userMission.completedAt,
                        restaurant.name,
                        mission.minSpendAmount,
                        userMission.spentAmount))
                .from(userMission)
                .innerJoin(userMission.mission, mission)
                .innerJoin(mission.restaurant, restaurant)
                .where(builder)
                .orderBy(userMission.id.desc())
                .limit(limit)
                .fetch();
    }
}
