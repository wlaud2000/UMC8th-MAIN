package com.microservice.demo.umc8th_main.domain.mission.converter;

import com.microservice.demo.umc8th_main.domain.mission.dto.request.MissionReqDTO;
import com.microservice.demo.umc8th_main.domain.mission.dto.response.MissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.Mission;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
}
