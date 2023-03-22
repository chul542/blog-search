package com.search.blog.service;

import static com.search.blog.config.MvcConfig.AUTHORIZATION;
import static com.search.blog.config.MvcConfig.X_NAVER_CLIENT_ID;
import static com.search.blog.config.MvcConfig.X_NAVER_CLINET_SECRET;

import com.search.blog.config.ServerNameEnum;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchDocument;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchMeta;
import com.search.blog.dto.service.KakaoBlogSearchApiDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.service.KakaoBlogSearchApiDto.KakaoBlogSearchApiRes;
import com.search.blog.dto.service.NaverBlogSearchApiDto.NaverBlogSearchApiReq;
import com.search.blog.dto.service.NaverBlogSearchApiDto.NaverBlogSearchApiRes;
import com.search.blog.exception.custom.PageLimitException;
import com.search.blog.mapstruct.BlogSearchMapper;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ApiCallServiceImpl implements ApiCallService {

  @Value("${secrets.kakao-rest-api-key}")
  private String kakaoRestApiKey;

  @Value("${secrets.x-naver-client-id-key}")
  private String xNaverClinetIdKey;

  @Value("${secrets.x-naver-client-secret-key}")
  private String xNaverClientSecretKey;

  private final WebClient webClientToKakaoApiServer;
  private final WebClient webClientToNaverApiServer;

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
        .header(AUTHORIZATION, kakaoRestApiKey)
        .retrieve()
        .bodyToMono(KakaoBlogSearchApiRes.class)
        .block();

    return BlogSearchWebRes
        .builder()
        .meta(
            BlogSearchMeta
                .builder()
                .total_count(Objects.requireNonNull(kakaoBlogSearchApiRes).getMeta().getTotal_count())
                .start(kakaoBlogSearchApiReq.getPage() * kakaoBlogSearchApiReq.getSize())
                .display(kakaoBlogSearchApiReq.getSize())
                .page(kakaoBlogSearchApiReq.getPage())
                .is_end(kakaoBlogSearchApiRes.getMeta().getIs_end())
                .search_source(String.valueOf(ServerNameEnum.KAKAO_API_SERVER))
                .build())
        .documents(
            kakaoBlogSearchApiRes.getDocuments().stream().map(BlogSearchDocument::of).toList())
        .build();
  }

  @Override
  public BlogSearchWebRes getNaverBlogSearchList(NaverBlogSearchApiReq naverBlogSearchApiReq) {

    NaverBlogSearchApiRes naverBlogSearchApiRes = webClientToNaverApiServer
        .get()
        .uri(uriBuilder -> uriBuilder.path("/v1/search/blog.json")
            .queryParam("query", naverBlogSearchApiReq.getQuery())
            .queryParam("display", naverBlogSearchApiReq.getDisplay())
            .queryParam("start", naverBlogSearchApiReq.getStart())
            .queryParam("sort", naverBlogSearchApiReq.getSort())
            .build()
        )
        .header(X_NAVER_CLIENT_ID, xNaverClinetIdKey)
        .header(X_NAVER_CLINET_SECRET, xNaverClientSecretKey)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
          throw new PageLimitException();
        })
        .bodyToMono(NaverBlogSearchApiRes.class)
        .block();

    Boolean isEnd = naverBlogSearchApiReq.getStart() + naverBlogSearchApiReq.getDisplay()
        > Objects.requireNonNull(naverBlogSearchApiRes).getTotal();

    return BlogSearchWebRes.builder()
        .meta(BlogSearchMeta.builder().total_count(naverBlogSearchApiRes.getTotal())
            .start(naverBlogSearchApiReq.getStart())
            .display(naverBlogSearchApiReq.getDisplay())
            .page(naverBlogSearchApiRes.getStart() / naverBlogSearchApiReq.getDisplay())
            .is_end(isEnd)
            .search_source(String.valueOf(ServerNameEnum.NAVER_API_SERVER)).build()
        )
        .documents(
            naverBlogSearchApiRes.getItems().stream()
                .map(BlogSearchMapper.INSTANCE::mapNaverToDocument)
                .toList()).build();


  }

}
