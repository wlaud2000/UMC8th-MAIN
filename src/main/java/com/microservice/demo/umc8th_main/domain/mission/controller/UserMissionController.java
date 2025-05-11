package com.microservice.demo.umc8th_main.domain.mission.controller;

import com.microservice.demo.umc8th_main.domain.mission.converter.UserMissionConverter;
import com.microservice.demo.umc8th_main.domain.mission.dto.request.UserMissionReqDTO;
import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.UserMission;
import com.microservice.demo.umc8th_main.domain.mission.service.UserMissionQueryService;
import com.microservice.demo.umc8th_main.domain.mission.service.UserMissionCommandService;
import com.microservice.demo.umc8th_main.global.apiPayload.base.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/missions/user")
@RequiredArgsConstructor
@Tag(name = "User Missions", description = "사용자 미션 관련 API")
public class UserMissionController {

    private final UserMissionCommandService userMissionCommandService;
    private final UserMissionQueryService userMissionQueryService;

    @PostMapping("/join")
    @Operation(summary = "미션 도전하기", description = "사용자가 미션에 도전합니다.")
    public ApiResponse<UserMissionResDTO.JoinResultDTO> joinMission(
            @Valid @RequestBody UserMissionReqDTO.JoinMissionDTO dto
    ) {
        UserMission userMission = userMissionCommandService.joinMission(dto);
        return ApiResponse.onSuccess(UserMissionConverter.toJoinResultDTO(userMission));
    }

    @GetMapping
    @Operation(summary = "사용자 미션 목록 조회", description = "진행 중이거나 완료된 사용자의 미션 목록을 페이징하여 조회합니다.")
    public ResponseEntity<List<UserMissionResDTO.UserMissionResponseDTO>> getUserMissions(
            @Parameter(description = "사용자 ID", required = true)
            @RequestParam Long userId,

            @Parameter(description = "미션 상태 필터 (ONGOING, COMPLETE 등), 미입력 시 전체 조회")
            @RequestParam(required = false) String status,

            @Parameter(description = "마지막으로 조회한 미션 ID (페이징용), 첫 페이지 조회 시 미입력")
            @RequestParam(required = false) Long lastId
    ) {
        List<UserMissionResDTO.UserMissionResponseDTO> missions =
                userMissionQueryService.getUserMissions(userId, status, lastId);
        return ResponseEntity.ok(missions);
    }
}
