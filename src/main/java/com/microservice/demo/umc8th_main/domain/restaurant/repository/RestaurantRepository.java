package com.microservice.demo.umc8th_main.domain.restaurant.repository;

import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
