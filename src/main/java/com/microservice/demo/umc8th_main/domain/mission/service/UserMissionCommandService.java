package com.microservice.demo.umc8th_main.domain.mission.service;

import com.microservice.demo.umc8th_main.domain.mission.converter.UserMissionConverter;
import com.microservice.demo.umc8th_main.domain.mission.dto.request.UserMissionReqDTO;
import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.Mission;
import com.microservice.demo.umc8th_main.domain.mission.entity.UserMission;
import com.microservice.demo.umc8th_main.domain.mission.repository.MissionRepository;
import com.microservice.demo.umc8th_main.domain.mission.repository.UserMissionRepository;
import com.microservice.demo.umc8th_main.domain.user.entity.User;
import com.microservice.demo.umc8th_main.domain.user.repository.UserRepository;
import com.microservice.demo.umc8th_main.global.apiPayload.code.ErrorStatus;
import com.microservice.demo.umc8th_main.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserMissionCommandService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    public UserMission joinMission(UserMissionReqDTO.JoinMissionDTO dto) {
        // 1. 사용자 조회 (하드코딩)
        User user = userRepository.findById(1L)  // 하드코딩된 사용자 ID
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 2. 미션 조회 (커스텀 어노테이션으로 이미 검증됨)
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        // 3. 미션 참여 객체 생성
        UserMission userMission = UserMissionConverter.toUserMission(user, mission);

        // 4. 저장 및 반환
        return userMissionRepository.save(userMission);
    }
}
