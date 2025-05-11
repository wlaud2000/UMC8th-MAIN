package com.microservice.demo.umc8th_main.domain.restaurant.service;

import com.microservice.demo.umc8th_main.domain.restaurant.converter.RestaurantConverter;
import com.microservice.demo.umc8th_main.domain.restaurant.dto.request.RestaurantReqDTO;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import com.microservice.demo.umc8th_main.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantCommandService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant createRestaurant(RestaurantReqDTO.CreateRestaurantDTO dto) {
        // 레스토랑 객체 생성
        Restaurant restaurant = RestaurantConverter.toRestaurant(dto);

        // 저장 및 반환
        return restaurantRepository.save(restaurant);
    }
}