package com.microservice.demo.umc8th_main.domain.mission.service;

import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.UserMission;
import com.microservice.demo.umc8th_main.domain.mission.repository.UserMissionRepository;
import com.microservice.demo.umc8th_main.domain.user.entity.User;
import com.microservice.demo.umc8th_main.domain.user.repository.UserRepository;
import com.microservice.demo.umc8th_main.global.apiPayload.code.ErrorStatus;
import com.microservice.demo.umc8th_main.global.apiPayload.exception.GeneralException;
import com.microservice.demo.umc8th_main.global.validation.validator.PageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    public List<UserMissionResDTO.UserMissionResponseDTO> getUserMissions(Long userId, String status, Long lastId) {
        return userMissionRepository.findUserMissionsWithPaging(userId, status, lastId, 10);
    }

    public boolean isAlreadyInProgress(Long userId, Long missionId) {
        return userMissionRepository.existsByUserIdAndMissionIdAndStatusIn(
                userId, missionId, List.of("ONGOING", "PENDING"));
    }

    // 내 미션 목록 조회 메서드 추가
    public Page<UserMission> getMyMissions(Long userId, String status, Integer page) {
        // 사용자 존재 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 페이지 번호를 0-based index로 변환
        int pageIndex = PageValidator.convertToPageIndex(page);

        // 상태가 지정된 경우 해당 상태의 미션만 조회
        if (status != null && !status.isEmpty()) {
            return userMissionRepository.findAllByUserIdAndStatus(userId, status, PageRequest.of(pageIndex, 10));
        }

        // 상태가 지정되지 않은 경우 모든 미션 조회
        return userMissionRepository.findAllByUserId(userId, PageRequest.of(pageIndex, 10));
    }
}