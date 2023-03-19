package com.search.blog.service;

import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiReq;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface ApiCallService {

  public Mono<String> getKakaoBlogSearchList(KakaoBlogSearchApiReq kakaoBlogSearchApiReq);



}
