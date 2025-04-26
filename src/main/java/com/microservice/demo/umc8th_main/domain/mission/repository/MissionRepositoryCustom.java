package com.microservice.demo.umc8th_main.domain.mission.repository;

import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;

import java.util.List;

public interface MissionRepositoryCustom {
    List<UserMissionResDTO.HomeMissionResponseDTO> findAvailableMissions(String regionName, Long userId, Long lastId, int limit);
}
