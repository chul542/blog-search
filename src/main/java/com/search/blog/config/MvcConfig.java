package com.search.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  public static final String BLOG_SEARCH_URI = "/v1/search/blog";

  public static final String AUTHORIZATION = "Authorization";

  public static final String X_NAVER_CLIENT_ID = "X-Naver-Client-Id";

  public static final String X_NAVER_CLINET_SECRET = "X-Naver-Client-Secret";

}
