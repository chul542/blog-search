package com.search.blog.exception.custom;

import com.search.blog.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private ExceptionCode exceptionCode;

  public CustomException(ExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }

  public ExceptionCode getErrorCode() {
    return this.exceptionCode;
  }

}