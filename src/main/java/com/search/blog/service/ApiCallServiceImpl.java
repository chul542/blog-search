package com.search.blog.service;

import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchDocument;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchMeta;
import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiRes;
import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiResDocument;
import com.search.blog.dto.web.NaverBlogSearchApiDto.NaverBlogSearchApiReq;
import com.search.blog.dto.web.NaverBlogSearchApiDto.NaverBlogSearchApiRes;
import com.search.blog.exception.custom.PageLimitException;
import com.search.blog.mapstruct.BlogSearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ApiCallServiceImpl implements ApiCallService {

  private final WebClient webClientToKakaoApiServer;

  @Override
  public BlogSearchWebRes getKakaoBlogSearchList(KakaoBlogSearchApiReq kakaoBlogSearchApiReq) {
    KakaoBlogSearchApiRes kakaoBlogSearchApiRes = webClientToKakaoApiServer
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
        .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
          throw new PageLimitException();
        })
        .bodyToMono(KakaoBlogSearchApiRes.class)
        .block();

    return BlogSearchWebRes.of(kakaoBlogSearchApiRes.getMeta(),
        (Slice<KakaoBlogSearchApiResDocument>) kakaoBlogSearchApiRes.getDocuments());

  }

  @Override
  public BlogSearchWebRes getNaverBlogSearchList(NaverBlogSearchApiReq naverBlogSearchApiReq) {
    NaverBlogSearchApiRes naverBlogSearchApiRes = webClientToKakaoApiServer
        .get()
        .uri(uriBuilder -> uriBuilder.path("/v1/search/blog.json")
            .queryParam("query", naverBlogSearchApiReq.getQuery())
            .queryParam("display", naverBlogSearchApiReq.getDisplay())
            .queryParam("start", naverBlogSearchApiReq.getStart())
            .queryParam("sort", naverBlogSearchApiReq.getSort())
            .build()
        )
        .header("X-Naver-Client-Id", "rNyjEScrdlU5n8Y_QnBR")
        .header("X-Naver-Client-Secret", "QblzCaOKNz")
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
          throw new PageLimitException();
        })
        .bodyToMono(NaverBlogSearchApiRes.class)
        .block();

    return BlogSearchWebRes.builder()
        .meta(BlogSearchMeta.builder().total_count(naverBlogSearchApiRes.getTotal())
            .start(naverBlogSearchApiReq.getStart())
            .display(naverBlogSearchApiReq.getDisplay())
            .page(naverBlogSearchApiRes.getStart() / naverBlogSearchApiReq.getDisplay()).build())
        .documents(
            naverBlogSearchApiRes.getItems().stream()
                .map(BlogSearchMapper.INSTANCE::mapNaverToDocument)
                .toList()).build();


  }

}
