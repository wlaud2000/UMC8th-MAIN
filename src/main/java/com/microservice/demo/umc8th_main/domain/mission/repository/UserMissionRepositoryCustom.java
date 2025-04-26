package com.microservice.demo.umc8th_main.domain.mission.repository;

import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;

import java.util.List;

public interface UserMissionRepositoryCustom {
    List<UserMissionResDTO.UserMissionResponseDTO> findUserMissionsWithPaging(Long userId, String status, Long lastId, int limit);
}
