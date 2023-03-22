package com.search.blog.dto.service;

import com.search.blog.exception.custom.DtoInstanceCreateException;
import lombok.Builder;
import lombok.Getter;

public class BlogSearchServiceDto {

  private BlogSearchServiceDto()  {
    throw new DtoInstanceCreateException();
  }

  @Builder
  @Getter
  public static class BlogSearchGetReq {

    private String query;

    private String sort;

    private Integer page;

    private Integer size;
  }
}
