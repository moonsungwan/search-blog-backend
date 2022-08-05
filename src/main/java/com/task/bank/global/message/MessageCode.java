package com.task.bank.global.message;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageCode {

	SUCCEED(HttpStatus.OK, "성공하였습니다."),
	REGISTERED(HttpStatus.OK, "등록되었습니다."),
    UPDATED(HttpStatus.OK, "수정되었습니다."),
    DELETED(HttpStatus.OK, "삭제되었습니다."),

    NO_CONTENT(HttpStatus.NO_CONTENT, "데이터가 없습니다."),

    FAILED(HttpStatus.BAD_REQUEST, "실패하였습니다."),

    BUSINESS_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "업무로직에서 에러가 발생했습니다."),
    ;

    private final HttpStatus status;
    private String message;

}