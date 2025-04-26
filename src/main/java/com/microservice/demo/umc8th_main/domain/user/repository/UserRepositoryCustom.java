package com.microservice.demo.umc8th_main.domain.user.repository;

import com.microservice.demo.umc8th_main.domain.user.dto.response.UserResDTO;

public interface UserRepositoryCustom {

    UserResDTO.UserProfileResDTO findUserProfile(Long userId);
}
