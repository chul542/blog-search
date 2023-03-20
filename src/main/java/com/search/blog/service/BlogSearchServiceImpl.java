package com.search.blog.service;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiRes;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BlogSearchServiceImpl implements BlogSearchService {

  private final ApiCallService apiCallService;

  @Override
  public BlogSearchWebRes getBlogSearchList(BlogSearchGetReq blogSearchGetReq) {

    return getKakaoBlogSearchList(blogSearchGetReq);
  }

  private BlogSearchWebRes getKakaoBlogSearchList(BlogSearchGetReq blogSearchGetReq) {
    System.out.println(blogSearchGetReq);
    return apiCallService.getKakaoBlogSearchList(KakaoBlogSearchApiReq.builder()
        .query(blogSearchGetReq.getQuery())
        .sort(blogSearchGetReq.getSort())
        .page(blogSearchGetReq.getPage())
        .size(blogSearchGetReq.getSize())
        .build());
  }
}
