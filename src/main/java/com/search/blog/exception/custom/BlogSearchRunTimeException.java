package com.search.blog.exception.custom;

import com.search.blog.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class BlogSearchRunTimeException extends RuntimeException {
  private ExceptionCode exceptionCode;

  public BlogSearchRunTimeException(ExceptionCode exceptionCode, String message, Throwable cause) {
    super(message, cause);
    this.exceptionCode = exceptionCode;
  }

  public BlogSearchRunTimeException(ExceptionCode exceptionCode, String message) {
    super(message);
    this.exceptionCode = exceptionCode;
  }

  public BlogSearchRunTimeException(ExceptionCode exceptionCode, Throwable cause) {
    super(cause);
    this.exceptionCode = exceptionCode;
  }

  public BlogSearchRunTimeException(ExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }

  public ExceptionCode getErrorCode() {
    return this.exceptionCode;
  }

}
