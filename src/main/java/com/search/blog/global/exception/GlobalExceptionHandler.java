package com.search.blog.global.exception;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.blog.global.message.MessageCode;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;
    
    @ExceptionHandler(value = { DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorResponse> handleDataException() {
        log.error("handleDataException throw DataIntegrityViolationException : {}", MessageCode.BUSINESS_EXCEPTION);
        return ErrorResponse.toResponseEntity(MessageCode.BUSINESS_EXCEPTION);
    }

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getMessageCode());
        return ErrorResponse.toResponseEntity(e.getMessageCode());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> feignExceptionHandler(FeignException feignException) throws JsonProcessingException {
        String responseJson = feignException.contentUTF8();
        Map<String, String> responseMap = objectMapper.readValue(responseJson, Map.class);

        log.error("handleFeignException throw FeignException : {}", responseMap.get("message"));
        
        return ResponseEntity
                .status(feignException.status())
                .body(ErrorResponse.builder()
                        .status(feignException.status())
                        .error(responseMap.get("errorType"))
                        .code(responseMap.get("errorType"))
                        .message(responseMap.get("message"))
                        .build()
                );
    }
}