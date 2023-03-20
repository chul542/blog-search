package com.search.blog.exception.custom;

import com.search.blog.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class BlogSearchException extends RuntimeException {
  private ExceptionCode exceptionCode;

  public BlogSearchException(ExceptionCode exceptionCode, String message, Throwable cause) {
    super(message, cause);
    this.exceptionCode = exceptionCode;
  }

  public BlogSearchException(ExceptionCode exceptionCode, String message) {
    super(message);
    this.exceptionCode = exceptionCode;
  }

  public BlogSearchException(ExceptionCode exceptionCode, Throwable cause) {
    super(cause);
    this.exceptionCode = exceptionCode;
  }

  public BlogSearchException(ExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }

}
