package com.search.blog.service;

import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.ApiCallDto.KakaoBlogSearchApiRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ApiCallServiceImpl implements ApiCallService {

  private final WebClient webClient;

  @Value("${api-server.kakao-api-server-url}")
  private String KAKAO_API_SERVER_URL;

  @Override
  public Mono<KakaoBlogSearchApiRes> getKakaoBlogSearchList(KakaoBlogSearchApiReq kakaoBlogSearchApiReq) {

    return webClient
        .get()
        .uri(uriBuilder -> uriBuilder.path(KAKAO_API_SERVER_URL + "/v2/search/blog ")
                .queryParam("query", kakaoBlogSearchApiReq.getQuery())
                .queryParam("sort", kakaoBlogSearchApiReq.getSort())
                .queryParam("page", kakaoBlogSearchApiReq.getPage())
                .queryParam("size", kakaoBlogSearchApiReq.getSize())
                .build()
        )
        .retrieve()
        .bodyToMono(KakaoBlogSearchApiRes.class);
  }
}
