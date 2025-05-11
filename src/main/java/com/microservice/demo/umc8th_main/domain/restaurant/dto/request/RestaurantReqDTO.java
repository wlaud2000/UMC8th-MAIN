package com.microservice.demo.umc8th_main.domain.restaurant.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RestaurantReqDTO {

    public record CreateRestaurantDTO(
            @NotBlank(message = "가게 이름은 필수입니다")
            @Size(max = 100, message = "가게 이름은 100자 이내여야 합니다")
            String name,

            @NotBlank(message = "카테고리는 필수입니다")
            @Size(max = 50, message = "카테고리는 50자 이내여야 합니다")
            String category,

            @NotBlank(message = "주소는 필수입니다")
            String address,

            @NotNull(message = "위도는 필수입니다")
            Float latitude,

            @NotNull(message = "경도는 필수입니다")
            Float longitude,

            @Size(max = 20, message = "전화번호는 20자 이내여야 합니다")
            String phoneNumber,

            String businessHours,

            String description
    ) {}
}
