package com.microservice.demo.umc8th_main.domain.mission.controller;

import com.microservice.demo.umc8th_main.domain.mission.converter.MissionConverter;
import com.microservice.demo.umc8th_main.domain.mission.dto.response.MissionResDTO;
import com.microservice.demo.umc8th_main.domain.mission.entity.Mission;
import com.microservice.demo.umc8th_main.domain.mission.service.MissionQueryService;
import com.microservice.demo.umc8th_main.global.apiPayload.base.ApiResponse;
import com.microservice.demo.umc8th_main.global.validation.annotation.ExistRestaurant;
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
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurant Missions", description = "가게 미션 관련 API")
public class RestaurantMissionController {

    private final MissionQueryService missionQueryService;

    @GetMapping("/{restaurantId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션 목록을 페이징하여 조회합니다. 페이지 번호는 1부터 시작합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지 번호는 1 이상이어야 합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "RESTAURANT4001", description = "해당 레스토랑이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게 ID", required = true),
            @Parameter(name = "page", description = "페이지 번호(1부터 시작)", required = true)
    })
    public ApiResponse<MissionResDTO.StoreMissionListDTO> getRestaurantMissions(
            @PathVariable @ExistRestaurant Long restaurantId,
            @Valid @ValidPage @RequestParam(name = "page") Integer page) {

        Page<Mission> missionPage = missionQueryService.getRestaurantMissions(restaurantId, page);
        return ApiResponse.onSuccess(MissionConverter.toStoreMissionListDTO(missionPage));
    }
}