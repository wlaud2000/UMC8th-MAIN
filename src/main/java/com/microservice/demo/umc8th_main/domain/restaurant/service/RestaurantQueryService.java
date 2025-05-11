package com.microservice.demo.umc8th_main.domain.restaurant.service;

import com.microservice.demo.umc8th_main.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantQueryService {

    private final RestaurantRepository restaurantRepository;

    public boolean existsById(Long restaurantId) {
        return restaurantRepository.existsById(restaurantId);
    }
}
