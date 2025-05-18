package com.microservice.demo.umc8th_main.domain.mission.controller;

import com.microservice.demo.umc8th_main.domain.mission.converter.UserMissionConverter;
import com.microservice.demo.umc8th_main.domain.mission.dto.response.UserMissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.UserMission;
import com.microservice.demo.umc8th_main.domain.mission.service.UserMissionQueryService;
import com.microservice.demo.umc8th_main.global.apiPayload.base.ApiResponse;
import com.microservice.demo.umc8th_main.global.validation.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
@Tag(name = "User Missions", description = "사용자 미션 관련 API")
public class MyMissionController {

    private final UserMissionQueryService userMissionQueryService;

    @GetMapping("/my")
    @Operation(summary = "내가 진행 중인 미션 목록 조회", description = "내가 진행 중인 미션 목록을 페이징하여 조회합니다. 페이지 번호는 1부터 시작합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호는 1 이상이어야 합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4003", description = "유효하지 않은 미션 상태입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자 ID", required = true),
            @Parameter(name = "status", description = "미션 상태 필터 (ONGOING, COMPLETE 등), 미입력 시 전체 조회"),
            @Parameter(name = "page", description = "페이지 번호(1부터 시작)", required = true)
    })
    public ApiResponse<UserMissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "status", required = false) String status,
            @Valid @ValidPage @RequestParam(name = "page") Integer page) {

        Page<UserMission> userMissionPage = userMissionQueryService.getMyMissions(userId, status, page);
        return ApiResponse.onSuccess(UserMissionConverter.toMyMissionListDTO(userMissionPage));
    }
}