package com.microservice.demo.umc8th_main.global.validation.validator;

import com.microservice.demo.umc8th_main.domain.restaurant.service.RestaurantQueryService;
import com.microservice.demo.umc8th_main.global.apiPayload.code.ErrorStatus;
import com.microservice.demo.umc8th_main.global.validation.annotation.ExistRestaurant;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantExistValidator implements ConstraintValidator<ExistRestaurant, Long> {

    private final RestaurantQueryService restaurantQueryService;

    @Override
    public void initialize(ExistRestaurant constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long restaurantId, ConstraintValidatorContext context) {
        // Service 레이어를 통해 검증 (Repository에 직접 접근하지 않음)
        boolean isValid = restaurantQueryService.existsById(restaurantId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            ErrorStatus.RESTAURANT_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
