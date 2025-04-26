package com.microservice.demo.umc8th_main.domain.mission.controller;

import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
@Tag(name = "Missions", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/available")
    @Operation(summary = "홈 화면 미션 목록 조회", description = "선택된 지역에서 도전 가능한 미션 목록을 페이징하여 조회합니다.")
    public ResponseEntity<List<UserMissionResDTO.HomeMissionResponseDTO>> getAvailableMissions(
            @Parameter(description = "선택한 지역명 (예: '안암동')", required = true)
            @RequestParam String regionName,

            @Parameter(description = "현재 로그인한 사용자 ID", required = true)
            @RequestParam Long userId,

            @Parameter(description = "마지막으로 조회한 미션 ID (페이징용), 첫 페이지 조회 시 미입력")
            @RequestParam(required = false) Long lastId
    ) {
        List<UserMissionResDTO.HomeMissionResponseDTO> missions =
                missionService.getAvailableMissions(regionName, userId, lastId);
        return ResponseEntity.ok(missions);
    }
}
