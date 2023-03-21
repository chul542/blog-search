package com.search.blog.config;

import static com.search.blog.config.MvcConfig.BLOG_SEARCH_URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .consumes(getConsumeContentTypes())
        .produces(getProduceContentTypes())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant(BLOG_SEARCH_URI + "/**"))
        .build()
        .apiInfo(metadata())
        .useDefaultResponseMessages(false)
        .genericModelSubstitutes(Optional.class);
  }

  private Set<String> getConsumeContentTypes() {
    Set<String> consumes = new HashSet<>();
    consumes.add("application/json;charset=UTF-8");
    consumes.add("application/x-www-form-urlencoded");
    return consumes;
  }

  private Set<String> getProduceContentTypes() {
    Set<String> produces = new HashSet<>();
    produces.add("application/json;charset=UTF-8");
    return produces;
  }

  private ApiInfo metadata() {
    return new ApiInfoBuilder()//
        .title("Blog Search")//
        .description("2023.03 - Blog Search")//
        .version("0.0.1")//
        .build();
  }
}
