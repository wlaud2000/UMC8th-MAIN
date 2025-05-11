package com.microservice.demo.umc8th_main.global.validation.validator;

import com.microservice.demo.umc8th_main.domain.mission.dto.request.UserMissionReqDTO;
import com.microservice.demo.umc8th_main.domain.mission.service.UserMissionQueryService;
import com.microservice.demo.umc8th_main.global.apiPayload.code.ErrorStatus;
import com.microservice.demo.umc8th_main.global.validation.annotation.MissionNotInProgress;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionNotInProgressValidator implements ConstraintValidator<MissionNotInProgress, UserMissionReqDTO.JoinMissionDTO> {

    private final UserMissionQueryService userMissionQueryService;

    @Override
    public void initialize(MissionNotInProgress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserMissionReqDTO.JoinMissionDTO dto, ConstraintValidatorContext context) {
        // 이미 도전 중인 미션인지 검증
        boolean alreadyInProgress = userMissionQueryService.isAlreadyInProgress(dto.userId(), dto.missionId());

        if (alreadyInProgress) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            ErrorStatus.MISSION_ALREADY_IN_PROGRESS.toString())
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}