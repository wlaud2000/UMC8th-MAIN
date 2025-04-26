package com.microservice.demo.umc8th_main.domain.user.repository;

import com.microservice.demo.umc8th_main.domain.user.dto.response.UserResDTO;
import com.microservice.demo.umc8th_main.domain.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public UserResDTO.UserProfileResDTO findUserProfile(Long userId) {
        QUser user = QUser.user;

        return queryFactory
                .select(Projections.constructor(UserResDTO.UserProfileResDTO.class,
                        user.id,
                        user.nickname,
                        user.email,
                        user.phoneNumber,
                        user.currentPoint,
                        new CaseBuilder()
                                .when(user.phoneNumber.isNotNull().and(user.phoneNumber.ne("")))
                                .then("VERIFIED")
                                .otherwise("UNVERIFIED")))
                .from(user)
                .where(user.id.eq(userId))
                .fetchOne();
    }
}
