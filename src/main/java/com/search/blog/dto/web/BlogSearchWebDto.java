package com.search.blog.dto.web;

import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiRes;
import com.search.blog.entity.BlogSearchHistoryEntity;
import com.search.blog.mapstruct.BlogSearchMapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    public BlogSearchWebRes() {
    }

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
