package com.microservice.demo.umc8th_main.domain.restaurant.converter;

import com.microservice.demo.umc8th_main.domain.restaurant.dto.request.RestaurantReqDTO;
import com.microservice.demo.umc8th_main.domain.restaurant.dto.response.RestaurantResDTO;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantConverter {

    public static Restaurant toRestaurant(RestaurantReqDTO.CreateRestaurantDTO dto) {
        return Restaurant.builder()
                .name(dto.name())
                .category(dto.category())
                .address(dto.address())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .phoneNumber(dto.phoneNumber())
                .businessHours(dto.businessHours())
                .description(dto.description())
                .rating(0.0f)
                .reviewCount(0)
                .isActive(true)
                .build();
    }

    public static RestaurantResDTO.CreateResultDTO toCreateResultDTO(Restaurant restaurant) {
        return RestaurantResDTO.CreateResultDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .category(restaurant.getCategory())
                .createdAt(restaurant.getCreatedAt())
                .build();
    }
}
