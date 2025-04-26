package com.microservice.demo.umc8th_main.domain.user.controller;

import com.microservice.demo.umc8th_main.domain.user.dto.response.UserResDTO;
import com.microservice.demo.umc8th_main.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "사용자 관련 API")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "사용자 프로필 조회", description = "사용자의 프로필 정보를 조회합니다.")
    public ResponseEntity<UserResDTO.UserProfileResDTO> getUserProfile(
            @Parameter(description = "사용자 ID", required = true)
            @RequestParam Long userId
    ) {
        UserResDTO.UserProfileResDTO profile = userService.getUserProfile(userId);
        return ResponseEntity.ok(profile);
    }
}
