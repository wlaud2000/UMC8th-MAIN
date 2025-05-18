package com.microservice.demo.umc8th_main.domain.mission.converter;

import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.Mission;
import com.microservice.demo.umc8th_main.domain.mission.entity.UserMission;
import com.microservice.demo.umc8th_main.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    // 내 미션 목록을 위한 메서드 추가
    public static UserMissionResDTO.MyMissionDTO toMyMissionDTO(UserMission userMission) {
        return UserMissionResDTO.MyMissionDTO.builder()
                .id(userMission.getId())
                .missionTitle(userMission.getMission().getTitle())
                .missionDescription(userMission.getMission().getDescription())
                .rewardPoints(userMission.getMission().getRewardPoints())
                .status(userMission.getStatus())
                .startedAt(userMission.getStartedAt())
                .completedAt(userMission.getCompletedAt())
                .restaurantName(userMission.getMission().getRestaurant().getName())
                .minSpendAmount(userMission.getMission().getMinSpendAmount())
                .spentAmount(userMission.getSpentAmount())
                .build();
    }

    public static UserMissionResDTO.MyMissionListDTO toMyMissionListDTO(Page<UserMission> userMissionPage) {
        List<UserMissionResDTO.MyMissionDTO> myMissionDTOList = userMissionPage.getContent().stream()
                .map(UserMissionConverter::toMyMissionDTO)
                .collect(Collectors.toList());

        return UserMissionResDTO.MyMissionListDTO.builder()
                .missionList(myMissionDTOList)
                .listSize(myMissionDTOList.size())
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .isFirst(userMissionPage.isFirst())
                .isLast(userMissionPage.isLast())
                .build();
    }
}
