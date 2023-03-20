package com.search.blog.exception.custom;

import com.search.blog.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class PageLimitException extends BlogSearchException {

  public PageLimitException() {
    super(ExceptionCode.PAGE_LIMIT_EXCESS);
  }

}
