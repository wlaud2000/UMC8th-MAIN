package com.microservice.demo.umc8th_main.domain.mission.converter;

import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.Mission;
import com.microservice.demo.umc8th_main.domain.mission.entity.UserMission;
import com.microservice.demo.umc8th_main.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMissionConverter {

    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status("ONGOING")
                .startedAt(LocalDateTime.now())
                .isRewarded(false)
                .build();
    }

    public static UserMissionResDTO.JoinResultDTO toJoinResultDTO(UserMission userMission) {
        return UserMissionResDTO.JoinResultDTO.builder()
                .id(userMission.getId())
                .missionId(userMission.getMission().getId())
                .status(userMission.getStatus())
                .startedAt(userMission.getStartedAt())
                .build();
    }
}
