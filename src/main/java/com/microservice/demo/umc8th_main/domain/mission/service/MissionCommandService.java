package com.microservice.demo.umc8th_main.domain.mission.service;

import com.microservice.demo.umc8th_main.domain.mission.converter.MissionConverter;
import com.microservice.demo.umc8th_main.domain.mission.dto.request.MissionReqDTO;
import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.Mission;
import com.microservice.demo.umc8th_main.domain.mission.repository.MissionRepository;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import com.microservice.demo.umc8th_main.domain.restaurant.repository.RestaurantRepository;
import com.microservice.demo.umc8th_main.global.apiPayload.code.ErrorStatus;
import com.microservice.demo.umc8th_main.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandService {

    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;

    public List<UserMissionResDTO.HomeMissionResponseDTO> getAvailableMissions(String regionName, Long userId, Long lastId) {
        // 기본 페이지 크기는 10으로 설정
        return missionRepository.findAvailableMissions(regionName, userId, lastId, 10);
    }

    @Transactional
    public Mission createMission(MissionReqDTO.CreateMissionDTO dto) {
        // 레스토랑 조회 (커스텀 어노테이션으로 이미 검증됨)
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(dto, restaurant);

        // 저장 및 반환
        return missionRepository.save(mission);
    }
}
