package com.microservice.demo.umc8th_main.domain.mission.service;

import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;

    public List<UserMissionResDTO.HomeMissionResponseDTO> getAvailableMissions(String regionName, Long userId, Long lastId) {
        // 기본 페이지 크기는 10으로 설정
        return missionRepository.findAvailableMissions(regionName, userId, lastId, 10);
    }
}
