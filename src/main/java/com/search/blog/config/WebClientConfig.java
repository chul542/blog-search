package com.search.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Value("${api-server.kakao-api-server-url}")
  private String kakaoApiServer;

  @Value("${api-server.naver-api-server-url}")
  private String naverApiServer;



  @Bean
  public WebClient webClientToKakaoApiServer() {
    return WebClient.builder()
        .baseUrl(kakaoApiServer)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @Bean
  public WebClient webClientToNaverApiServer() {
    return WebClient.builder()
        .baseUrl(naverApiServer)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}
