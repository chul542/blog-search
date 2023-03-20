package com.search.blog.mapstruct;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiRes;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.BlogSearchWebDto.KeywordInfo;
import com.search.blog.dto.web.BlogSearchWebDto.TopTenKeywordRes;
import com.search.blog.entity.BlogSearchHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import reactor.core.publisher.Mono;

@Mapper
public interface BlogSearchMapper {

  BlogSearchMapper INSTANCE = Mappers.getMapper(BlogSearchMapper.class);

//  @Mapping(source = "query", target = "keyword")
//  BlogSearchHistoryEntity toBlogSearchHistoryEntity(BlogSearchGetReq blogSearchGetReq)

  // KakaoBlogSearchApiRes -> BlogSearchWebRes

  KeywordInfo toKeyTenKeyWord(BlogSearchHistoryEntity historyEntity);

}
