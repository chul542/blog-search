package com.search.blog.dto.web;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class BlogSearchWebDto {

  private BlogSearchWebDto() {
    throw new IllegalStateException("DTO instance is not allowed");
  }

  @Builder
  @Getter
  public static class BlogSearchWebRes {
    private BlogSearchMeta meta;

    private List<BlogSearchDocument> documents;

  }

  @Builder
  @Getter
  public static class BlogSearchMeta {

    private Integer total_count;

    private Integer pageable_count;

    private Boolean is_end;
  }

  @Builder
  @Getter
  public static class BlogSearchDocument {
    private String title;

    private String contents;

    private String url;

    private String blogname;

    private String thumbnail;

    private String dateTime;
  }

}
