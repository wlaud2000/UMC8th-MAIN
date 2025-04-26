package com.microservice.demo.umc8th_main.domain.mission.repository;

import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.QMission;
import com.microservice.demo.umc8th_main.domain.mission.entity.QUserMission;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.QRestaurant;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserMissionResDTO.HomeMissionResponseDTO> findAvailableMissions(String regionName, Long userId, Long lastId, int limit) {
        QMission mission = QMission.mission;
        QRestaurant restaurant = QRestaurant.restaurant;
        QUserMission userMission = QUserMission.userMission;

        LocalDate today = LocalDate.now();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(mission.status.eq("ACTIVE"));
        builder.and(mission.startDate.loe(today)); // 시작일이 오늘 이전이거나 오늘
        builder.and(mission.endDate.goe(today));   // 종료일이 오늘 이후이거나 오늘
        builder.and(restaurant.address.like("%" + regionName + "%"));

        // 커서 기반 페이징
        if (lastId != null) {
            builder.and(mission.id.lt(lastId));
        }

        // 서브쿼리: 사용자가 이미 참여한 미션 제외
        return queryFactory
                .select(Projections.constructor(UserMissionResDTO.HomeMissionResponseDTO.class,
                        mission.id,
                        mission.title,
                        mission.description,
                        mission.rewardPoints,
                        restaurant.name,
                        restaurant.category,
                        mission.minSpendAmount,
                        mission.endDate,
                        Expressions.numberTemplate(Integer.class,
                                "DATEDIFF({0}, {1})", mission.endDate, Expressions.currentDate()).as("daysRemaining")))
                .from(mission)
                .innerJoin(mission.restaurant, restaurant)
                .where(builder)
                .where(JPAExpressions
                        .selectOne()
                        .from(userMission)
                        .where(userMission.mission.eq(mission)
                                .and(userMission.user.id.eq(userId)))
                        .notExists())
                .orderBy(mission.id.desc())
                .limit(limit)
                .fetch();
    }
}
