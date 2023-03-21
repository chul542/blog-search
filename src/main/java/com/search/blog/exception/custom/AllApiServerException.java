package com.search.blog.exception.custom;

import com.search.blog.exception.ExceptionCode;

public class AllApiServerException extends BlogSearchException {

  public AllApiServerException() {

    super(ExceptionCode.ALL_API_SERVER_EXCEPTION);
  }
}