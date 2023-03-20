package com.search.blog.service;

import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.NaverBlogSearchApiDto.NaverBlogSearchApiReq;

public interface ApiCallService {

  public BlogSearchWebRes getKakaoBlogSearchList(KakaoBlogSearchApiReq kakaoBlogSearchApiReq);

  public BlogSearchWebRes getNaverBlogSearchList(NaverBlogSearchApiReq naverBlogSearchApiReq);

}
