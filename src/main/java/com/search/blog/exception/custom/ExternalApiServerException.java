package com.search.blog.exception.custom;

import com.search.blog.exception.ExceptionCode;

public class ExternalApiServerException extends BlogSearchRunTimeException {

  public ExternalApiServerException() {

    super(ExceptionCode.ALL_API_SERVER_EXCEPTION);
  }
}