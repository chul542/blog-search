package com.search.blog.dto.service;

import com.search.blog.exception.custom.DtoInstanceCreateException;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class KakaoBlogSearchApiDto {

  private KakaoBlogSearchApiDto() {
    throw new DtoInstanceCreateException();
  }

  @Builder
  @Getter
  public static class KakaoBlogSearchApiReq {

    private String query;

    private String sort;

    private Integer page;

    private Integer size;
  }



  @Data
  public static class KakaoBlogSearchApiRes {

    private KakaoBlogSearchApiResMeta meta;

    private List<KakaoBlogSearchApiResDocument> documents;
  }

  @Data
  public static class KakaoBlogSearchApiResMeta {

    private Integer total_count;

    private Integer pageable_count;

    private Boolean is_end;
  }

  @Data
  public static class KakaoBlogSearchApiResDocument {

    private String title;

    private String contents;

    private String url;

    private String blogname;

    private String thumbnail;

    private String dateTime;
  }
}
