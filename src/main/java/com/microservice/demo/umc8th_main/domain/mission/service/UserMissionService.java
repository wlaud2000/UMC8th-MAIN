package com.microservice.demo.umc8th_main.domain.mission.service;

import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;

    public List<UserMissionResDTO.UserMissionResponseDTO> getUserMissions(Long userId, String status, Long lastId) {
        // 기본 페이지 크기는 10으로 설정
        return userMissionRepository.findUserMissionsWithPaging(userId, status, lastId, 10);
    }
}
