package com.microservice.demo.umc8th_main.domain.temp.controller;

import com.microservice.demo.umc8th_main.domain.temp.converter.TempConverter;
import com.microservice.demo.umc8th_main.domain.temp.dto.TempResponse;
import com.microservice.demo.umc8th_main.domain.temp.service.TempQueryService;
import com.microservice.demo.umc8th_main.global.apiPayload.base.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI(){
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        tempQueryService.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }

    @GetMapping("/error500")
    public ApiResponse<String> cause500Error() {
        // 강제로 NullPointerException 발생
        String nullString = null;
        nullString.length();  // NullPointerException 발생
        return ApiResponse.onSuccess("이 코드는 실행되지 않습니다");
    }
}