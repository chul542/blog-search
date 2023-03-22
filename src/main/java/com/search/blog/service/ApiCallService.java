package com.search.blog.service;

import com.search.blog.dto.service.KakaoBlogSearchApiDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.service.NaverBlogSearchApiDto.NaverBlogSearchApiReq;
import org.springframework.stereotype.Service;

@Service
public interface ApiCallService {

  public BlogSearchWebRes getKakaoBlogSearchList(KakaoBlogSearchApiReq kakaoBlogSearchApiReq);

  public BlogSearchWebRes getNaverBlogSearchList(NaverBlogSearchApiReq naverBlogSearchApiReq);

}
