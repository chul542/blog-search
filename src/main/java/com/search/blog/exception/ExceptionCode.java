package com.search.blog.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

  // Global
  INTERNAL(HttpStatus.BAD_REQUEST, "E001", "Internal Error Occurred"),
  METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "E002", "Method Not Allowed"),
  INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "E003", "Invalid Input Value"),
  // Custom Exception
  PAGE_LIMIT_EXCESS(HttpStatus.BAD_REQUEST, "B001", "Requested Page Is More Than Max");

  private final HttpStatus status;
  private final String code;
  private final String message;

  public String getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }
}
