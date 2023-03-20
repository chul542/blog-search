package com.search.blog.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSearchMapper {

  BlogSearchMapper INSTANCE = Mappers.getMapper(BlogSearchMapper.class);

  // KakaoBlogSearchApiRes -> BlogSearchWebRes

}
