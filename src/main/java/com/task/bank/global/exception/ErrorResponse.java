package com.task.bank.global.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import com.task.bank.global.message.MessageCode;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final int status;

    private final String error;

    private final String code;

    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(MessageCode messageCode) {
        return ResponseEntity
                .status(messageCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(messageCode.getStatus().value())
                        .error(messageCode.getStatus().name())
                        .code(messageCode.name())
                        .message(messageCode.getMessage())
                        .build()
                );
    }

}