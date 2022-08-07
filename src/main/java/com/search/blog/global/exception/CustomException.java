package com.search.blog.global.exception;

import com.search.blog.global.message.MessageCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final MessageCode messageCode;

}