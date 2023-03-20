package com.search.blog.dto.web;

import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiRes;
import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Slice;
import reactor.core.publisher.Mono;

public class BlogSearchWebDto {

  private BlogSearchWebDto() {
    throw new IllegalStateException("DTO instance is not allowed");
  }


 @Data
  public static class BlogSearchWebRes {

    public BlogSearchWebRes() {}
    private BlogSearchMeta meta;

    private List<BlogSearchDocument> documents;

  }


  @Data
  public static class BlogSearchMeta {

    private Integer total_count;

    private Integer pageable_count;

    private Boolean is_end;
  }


  @Data
  public static class BlogSearchDocument {
    private String title;

    private String contents;

    private String url;

    private String blogname;

    private String thumbnail;

    private String dateTime;
  }

}
