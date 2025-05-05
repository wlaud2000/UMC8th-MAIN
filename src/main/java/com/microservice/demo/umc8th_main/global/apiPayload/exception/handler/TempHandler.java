package com.microservice.demo.umc8th_main.global.apiPayload.exception.handler;

import com.microservice.demo.umc8th_main.global.apiPayload.code.BaseErrorCode;
import com.microservice.demo.umc8th_main.global.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
