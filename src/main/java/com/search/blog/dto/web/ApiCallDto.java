package com.search.blog.dto.web;

import lombok.Builder;
import lombok.Getter;

public class ApiCallDto {

  private ApiCallDto() {
    throw new IllegalStateException("DTO instance is not allowed");
  }

  @Builder
  @Getter
  public static class KakaoBlogSearchApiReq {
    private String query;

    private String sort;

    private Integer page;

    private Integer size;
  }

  @Builder
  @Getter
  public static class KakaoBlogSearchApiRes {
    private Integer total_count;

    private Integer pageable_count;

    private Boolean is_end;
  }

}
