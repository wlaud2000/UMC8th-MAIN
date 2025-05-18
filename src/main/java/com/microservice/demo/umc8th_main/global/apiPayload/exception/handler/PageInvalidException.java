package com.microservice.demo.umc8th_main.global.apiPayload.exception.handler;

import com.microservice.demo.umc8th_main.global.apiPayload.code.BaseErrorCode;
import com.microservice.demo.umc8th_main.global.apiPayload.exception.GeneralException;

public class PageInvalidException extends GeneralException {

    public PageInvalidException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}