package com.search.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClintConfig {

  @Value("${api-server.kakao-api-server-url}")
  private String KAKAO_API_SERVER;

  @Bean
  public WebClient webClientToKakaoApiServer() {
    return WebClient.builder()
        .baseUrl(KAKAO_API_SERVER)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}
