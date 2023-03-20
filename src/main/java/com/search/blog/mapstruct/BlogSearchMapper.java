package com.search.blog.mapstruct;

import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchDocument;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchMeta;
import com.search.blog.dto.web.BlogSearchWebDto.KeywordInfo;
import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiResDocument;
import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiResMeta;
import com.search.blog.dto.web.NaverBlogSearchApiDto.NaverBlogSearchItem;
import com.search.blog.entity.BlogSearchHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSearchMapper {

  BlogSearchMapper INSTANCE = Mappers.getMapper(BlogSearchMapper.class);

//  @Mapping(source = "query", target = "keyword")
//  BlogSearchHistoryEntity toBlogSearchHistoryEntity(BlogSearchGetReq blogSearchGetReq)

  // KakaoBlogSearchApiRes -> BlogSearchWebRes

  KeywordInfo toKeyTenKeyWord(BlogSearchHistoryEntity historyEntity);


  @Mapping(target = "start", ignore = true)
  @Mapping(target = "display", ignore = true)
  @Mapping(target = "page", ignore = true)
  BlogSearchMeta mapToMeta(KakaoBlogSearchApiResMeta meta);


  BlogSearchDocument mapKakaoToDocument(KakaoBlogSearchApiResDocument document);

  @Mapping(source = "title", target = "title")
  @Mapping(source = "link", target = "url")
  @Mapping(source = "description", target = "contents")
  @Mapping(source = "bloggername", target = "blogname")
  @Mapping(source = "postdate", target = "dateTime", dateFormat = "yyyyMMdd")
  @Mapping(target = "thumbnail", ignore = true)
  BlogSearchDocument mapNaverToDocument(NaverBlogSearchItem item);





}
