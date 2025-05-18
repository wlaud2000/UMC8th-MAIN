package com.microservice.demo.umc8th_main.domain.mission.service;

import com.microservice.demo.umc8th_main.domain.mission.entity.Mission;
import com.microservice.demo.umc8th_main.domain.mission.repository.MissionRepository;
import com.microservice.demo.umc8th_main.domain.restaurant.entity.Restaurant;
import com.microservice.demo.umc8th_main.domain.restaurant.repository.RestaurantRepository;
import com.microservice.demo.umc8th_main.global.apiPayload.code.ErrorStatus;
import com.microservice.demo.umc8th_main.global.apiPayload.exception.GeneralException;
import com.microservice.demo.umc8th_main.global.validation.validator.PageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;

    public boolean existsById(Long missionId) {
        return missionRepository.existsById(missionId);
    }

    public Page<Mission> getRestaurantMissions(Long restaurantId, Integer page) {
        // 페이지 번호를 0-based index로 변환
        int pageIndex = PageValidator.convertToPageIndex(page);

        // 레스토랑 조회
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        // 레스토랑의 미션 목록 조회
        return missionRepository.findAllByRestaurant(restaurant, PageRequest.of(pageIndex, 10));
    }
}