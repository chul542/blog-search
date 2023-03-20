package com.search.blog.service;

import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiRes;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

public interface ApiCallService {

  public BlogSearchWebRes getKakaoBlogSearchList(KakaoBlogSearchApiReq kakaoBlogSearchApiReq);



}
