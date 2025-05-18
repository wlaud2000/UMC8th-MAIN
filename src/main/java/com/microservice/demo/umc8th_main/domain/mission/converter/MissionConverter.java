package com.microservice.demo.umc8th_main.domain.mission.converter;

import com.microservice.demo.umc8th_main.domain.mission.dto.request.MissionReqDTO;
import com.microservice.demo.umc8th_main.domain.mission.dto.response.MissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.Mission;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionConverter {

    public static Mission toMission(MissionReqDTO.CreateMissionDTO dto, Restaurant restaurant) {
        return Mission.builder()
                .restaurant(restaurant)
                .title(dto.title())
                .description(dto.description())
                .rewardPoints(dto.rewardPoints())
                .minSpendAmount(dto.minSpendAmount())
                .status("ACTIVE")
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .build();
    }

    public static MissionResDTO.CreateResultDTO toCreateResultDTO(Mission mission) {
        return MissionResDTO.CreateResultDTO.builder()
                .id(mission.getId())
                .title(mission.getTitle())
                .restaurantName(mission.getRestaurant().getName())
                .rewardPoints(mission.getRewardPoints())
                .startDate(mission.getStartDate())
                .endDate(mission.getEndDate())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    // 가게 미션 목록을 위한 메서드 추가
    public static MissionResDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {
        return MissionResDTO.StoreMissionDTO.builder()
                .id(mission.getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .rewardPoints(mission.getRewardPoints())
                .minSpendAmount(mission.getMinSpendAmount())
                .status(mission.getStatus())
                .startDate(mission.getStartDate())
                .endDate(mission.getEndDate())
                .build();
    }

    public static MissionResDTO.StoreMissionListDTO toStoreMissionListDTO(Page<Mission> missionPage) {
        List<MissionResDTO.StoreMissionDTO> missionDTOList = missionPage.getContent().stream()
                .map(MissionConverter::toStoreMissionDTO)
                .collect(Collectors.toList());

        return MissionResDTO.StoreMissionListDTO.builder()
                .missionList(missionDTOList)
                .listSize(missionDTOList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
