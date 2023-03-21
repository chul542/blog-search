package com.search.blog.dto.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiResDocument;
import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiResMeta;
import com.search.blog.dto.web.NaverBlogSearchApiDto.NaverBlogSearchItem;
import com.search.blog.entity.BlogSearchHistoryEntity;
import com.search.blog.mapstruct.BlogSearchMapper;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Slice;

public class BlogSearchWebDto {

  private BlogSearchWebDto() {
    throw new IllegalStateException("DTO instance is not allowed");
  }


  @Data
  @Builder
  public static class BlogSearchWebRes {

    private BlogSearchMeta meta;

    private List<BlogSearchDocument> documents;

    public static BlogSearchWebRes of(KakaoBlogSearchApiResMeta meta, List<KakaoBlogSearchApiResDocument> documents) {
      return BlogSearchWebRes.builder()
          .meta(BlogSearchMeta.of(meta))
          .documents(
              documents.stream().map(BlogSearchDocument::of).toList()
          )
          .build();
    }

  }


  @Data
  @Builder
  public static class BlogSearchMeta {

    private Integer total_count;

    private Integer start;

    private Integer display;

    private Integer page;

    private Boolean is_end;

    public static BlogSearchMeta of(KakaoBlogSearchApiResMeta meta) {
      return BlogSearchMapper.INSTANCE.mapToMeta(meta);
    }
  }


  @Data
  @JsonInclude(Include.NON_NULL)
  public static class BlogSearchDocument {

    private String title;

    private String contents;

    private String url;

    private String blogname;

    private String thumbnail;

    private String dateTime;

    public static BlogSearchDocument of(KakaoBlogSearchApiResDocument document) {
      return BlogSearchMapper.INSTANCE.mapKakaoToDocument(document);
    }

    public static BlogSearchDocument of(NaverBlogSearchItem item) {
      return BlogSearchMapper.INSTANCE.mapNaverToDocument(item);
    }
  }


  @Getter
  @Builder
  public static class TopTenKeywordRes {

    private Boolean hasMore;

    private List<KeywordInfo> keywordList;

    public static TopTenKeywordRes of(Slice<BlogSearchHistoryEntity> keywordList) {
      int size = keywordList.getSize();
      ListIterator<BlogSearchHistoryEntity> it = keywordList.getContent().listIterator(size);
      List<KeywordInfo> reversedList = Stream.generate(it::previous)
          .limit(size)
          .map(KeywordInfo::of)
          .collect(Collectors.toList());
      Collections.reverse(reversedList);
      return TopTenKeywordRes.builder()
          .hasMore(keywordList.hasNext())
          .keywordList(
              reversedList
          ).build();
    }
  }

  @Getter
  @Builder
  public static class KeywordInfo {

    private String keyword;

    private Long numberOfSearch;

    private String createdAt;

    private String updatedAt;

    public static KeywordInfo of(BlogSearchHistoryEntity historyEntity) {
      return BlogSearchMapper.INSTANCE.toKeyTenKeyWord(historyEntity);
    }
  }

}
