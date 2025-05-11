package com.microservice.demo.umc8th_main.domain.restaurant.controller;

import com.microservice.demo.umc8th_main.domain.restaurant.converter.RestaurantConverter;
import com.microservice.demo.umc8th_main.domain.restaurant.dto.request.RestaurantReqDTO;
import com.microservice.demo.umc8th_main.domain.restaurant.dto.response.RestaurantResDTO;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import com.microservice.demo.umc8th_main.domain.restaurant.service.RestaurantCommandService;
import com.microservice.demo.umc8th_main.global.apiPayload.base.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurants", description = "레스토랑 관련 API")
public class RestaurantController {

    private final RestaurantCommandService restaurantCommandService;

    @PostMapping
    @Operation(summary = "레스토랑 추가", description = "특정 지역에 새로운 레스토랑을 추가합니다.")
    public ApiResponse<RestaurantResDTO.CreateResultDTO> createRestaurant(
            @Valid @RequestBody RestaurantReqDTO.CreateRestaurantDTO dto
    ) {
        Restaurant restaurant = restaurantCommandService.createRestaurant(dto);
        return ApiResponse.onSuccess(RestaurantConverter.toCreateResultDTO(restaurant));
    }
}