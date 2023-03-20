package com.search.blog.service;

import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiRes;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ApiCallServiceImpl implements ApiCallService {

  private final WebClient webClientToKakaoApiServer;

  @Override
  public BlogSearchWebRes getKakaoBlogSearchList(KakaoBlogSearchApiReq kakaoBlogSearchApiReq) {
    return webClientToKakaoApiServer
        .get()
        .uri(uriBuilder -> uriBuilder.path("/v2/search/blog")
                .queryParam("query", kakaoBlogSearchApiReq.getQuery())
                .queryParam("sort", kakaoBlogSearchApiReq.getSort())
                .queryParam("page", kakaoBlogSearchApiReq.getPage())
                .queryParam("size", kakaoBlogSearchApiReq.getSize())
                .build()
        )
        .header("Authorization", "KakaoAK b59375d0e881ccac07d04996b2496fd3")
        .retrieve()
        .bodyToMono(BlogSearchWebRes.class)
        .block();
  }
}
