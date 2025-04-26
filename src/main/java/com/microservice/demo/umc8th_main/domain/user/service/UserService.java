package com.microservice.demo.umc8th_main.domain.user.service;

import com.microservice.demo.umc8th_main.domain.user.dto.response.UserResDTO;
import com.microservice.demo.umc8th_main.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResDTO.UserProfileResDTO getUserProfile(Long userId) {
        return userRepository.findUserProfile(userId);
    }
}
