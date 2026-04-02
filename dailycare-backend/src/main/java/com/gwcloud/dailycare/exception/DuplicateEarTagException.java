package com.gwcloud.dailycare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * 이미 등록된 개체번호일 때 발생하는 예외
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "이미 등록된 개체번호입니다.")
public class DuplicateEarTagException extends RuntimeException {
}