package com.task.bank.global.entity;

import com.task.bank.global.message.MessageCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseEntity<T> {

    private boolean success = true;
    private T response = null;
    private String message = MessageCode.SUCCEED.getMessage();

    public ApiResponseEntity(T response) {
        this.response = response;
    }

    public ApiResponseEntity(T response, MessageCode message) {
        this.response = response;
        this.message = message.getMessage();
    }

    public ApiResponseEntity(MessageCode message) {
        this.message = message.getMessage();
    }

    public ApiResponseEntity(T response, int result) {
    	this.response = response;
    	this.message = result > 0 ? MessageCode.SUCCEED.getMessage() : MessageCode.FAILED.getMessage();
    }

    public ApiResponseEntity(boolean success, T response, MessageCode message) {
        this.success = success;
        this.response = response;
        this.message = message.getMessage();
    }

}