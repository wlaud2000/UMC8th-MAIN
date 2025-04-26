package com.microservice.demo.umc8th_main.domain.user.dto.response;

public class UserResDTO {

    public record UserProfileResDTO(
            Long id,
            String nickname,
            String email,
            String phoneNumber,
            int currentPoint,
            String phoneVerificationStatus
    ) {
    }
}
