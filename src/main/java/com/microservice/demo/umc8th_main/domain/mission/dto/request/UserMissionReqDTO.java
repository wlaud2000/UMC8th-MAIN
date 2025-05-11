package com.microservice.demo.umc8th_main.domain.mission.dto.request;

import com.microservice.demo.umc8th_main.global.validation.annotation.ExistMission;
import com.microservice.demo.umc8th_main.global.validation.annotation.MissionNotInProgress;
import jakarta.validation.constraints.NotNull;

public class UserMissionReqDTO {

    @MissionNotInProgress
    public record JoinMissionDTO(
            // 로그인 기능 없이 하드코딩 예정이므로 검증 생략
            Long userId,

            @NotNull(message = "미션 ID는 필수입니다")
            @ExistMission
            Long missionId
    ) {}
}
