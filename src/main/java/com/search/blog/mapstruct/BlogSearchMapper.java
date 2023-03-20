package com.search.blog.mapstruct;

import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiRes;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import reactor.core.publisher.Mono;

@Mapper
public interface BlogSearchMapper {

  BlogSearchMapper INSTANCE = Mappers.getMapper(BlogSearchMapper.class);

  BlogSearchWebRes toBlogSearchWebRes(Mono<KakaoBlogSearchApiRes> rest);

  // KakaoBlogSearchApiRes -> BlogSearchWebRes

}
