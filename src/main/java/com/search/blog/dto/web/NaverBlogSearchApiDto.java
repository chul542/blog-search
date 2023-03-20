package com.search.blog.dto.web;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class NaverBlogSearchApiDto {

  private NaverBlogSearchApiDto () {
    throw new IllegalStateException("DTO instance is not allowed");
  }

  @Builder
  @Getter
  public static class NaverBlogSearchApiReq {
    private String query;

    private Integer display;

    private Integer start;

    private String sort;
  }

  @Builder
  @Getter
  public static class NaverBlogSearchApiRes {
    private String lastBuildDate;

    private Integer total;

    private Integer start;

    private Integer display;

    private List<NaverBlogSearchItem> items;
  }

  @Getter
  @Builder
  public static class NaverBlogSearchItem {
    private String title;

    private String link;

    private String description;

    private String bloggername;

    private String bloggerlink;

    private String postdate;
  }


}
