package com.microservice.demo.umc8th_main.global.validation.validator;

import com.microservice.demo.umc8th_main.global.validation.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public void initialize(ValidPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        boolean isValid = page != null && page >= 1;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "PAGE_INVALID")
                    .addConstraintViolation();
        }

        return isValid;
    }

    // 1-based 페이지 번호를 Spring Data JPA의 0-based 페이지 번호로 변환
    public static int convertToPageIndex(int pageNumber) {
        return pageNumber - 1;
    }
}