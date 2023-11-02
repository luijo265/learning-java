package com.aprendekomodo.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends Exception {

    private final String businessErrorMessage;

    /*public BusinessException(String businessErrorMessage){
        super(businessErrorMessage);
        this.businessErrorMessage = businessErrorMessage;
    }*/
}
