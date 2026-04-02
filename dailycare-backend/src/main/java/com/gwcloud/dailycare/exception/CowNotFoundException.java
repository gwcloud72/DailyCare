package com.gwcloud.dailycare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * 존재하지 않는 젖소를 조회할 때 발생하는 예외
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "해당 개체를 찾을 수 없습니다.")
public class CowNotFoundException extends RuntimeException {
}