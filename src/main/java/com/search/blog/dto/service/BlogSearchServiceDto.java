package com.search.blog.dto.service;

import lombok.Builder;
import lombok.Getter;

public class BlogSearchServiceDto {

  private BlogSearchServiceDto()  {
    throw new IllegalStateException("DTO instance is not allowed");
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
