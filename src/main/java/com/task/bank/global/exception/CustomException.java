package com.task.bank.global.exception;

import com.task.bank.global.message.MessageCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final MessageCode messageCode;

}