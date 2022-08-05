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
    LOGOUT(HttpStatus.OK, "로그아웃 되었습니다."),
    
    NO_CONTENT(HttpStatus.NO_CONTENT, "데이터가 없습니다."),

    FAILED(HttpStatus.BAD_REQUEST, "실패하였습니다."),
    
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호입니다."),
    
    BUSINESS_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "업무로직에서 에러가 발생했습니다."),
    
    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    MEMBER_EXISTING(HttpStatus.CONFLICT, "이미 등록된 유저 정보입니다."),
    ;

    private final HttpStatus status;
    private String message;

}