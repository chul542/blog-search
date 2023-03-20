package com.search.blog.dto.web;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class KakaoBlogSearchApiDto {

  private KakaoBlogSearchApiDto() {
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
    private KakaoBlogSearchApiResMeta meta;
    private List<KakaoBlogSearchApiResDocument> documents;

  }

  @Builder
  @Getter
  public static class KakaoBlogSearchApiResMeta {
    private Integer total_count;

    private Integer pageable_count;

    private Boolean is_end;
  }

  @Builder
  @Getter
  public static class KakaoBlogSearchApiResDocument {
    private String title;

    private String contents;

    private String url;

    private String blogname;

    private String thumbnail;

    private String dateTime;
  }

}
