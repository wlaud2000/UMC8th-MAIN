package com.microservice.demo.umc8th_main.domain.mission.service;

import com.microservice.demo.umc8th_main.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final MissionRepository missionRepository;

    public boolean existsById(Long missionId) {
        return missionRepository.existsById(missionId);
    }
}