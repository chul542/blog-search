package com.search.blog.controller;

import static com.search.blog.config.MvcConfig.BLOG_SEARCH_URI;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.BlogSearchWebDto.TopTenKeywordRes;
import com.search.blog.exception.custom.PageLimitException;
import com.search.blog.service.BlogSearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = BLOG_SEARCH_URI)
public class BlogSearchController {

  private final BlogSearchService blogSearchService;

  @ApiOperation(value = "Ping-Pong Api", notes = "This is server test api")
  @GetMapping(path = "/test")
  public String testFunc() {
    return "Ping! Pong!";
  }

  @ApiOperation(value = "Search Blog Using Keyword", notes = "It returns the blog information from the Kakao Api server. If it triggers the error, it returns the result from the Naver Api server.")
  @GetMapping()
  public BlogSearchWebRes getBlogSearchList(
      @RequestParam(value = "query") String query,
      @RequestParam(value = "sort", required = false, defaultValue = "accuracy")
      @Pattern(regexp = "accuracy|recency", message = "Invalid query parameter value. "
          + "Allowed values are 'accuracy' and 'recency'.") String sort,
      @RequestParam(value = "page", required = false, defaultValue = "1") @Min(1) @Max(50) Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") @Min(1) @Max(50) Integer size
  ) throws PageLimitException {
    return blogSearchService.getBlogSearchList(BlogSearchGetReq
        .builder()
        .query(query)
        .sort(sort)
        .page(page)
        .size(size)
        .build());
  }

  @Operation(summary = "Popular Keyword")
  @GetMapping(path = "/popular-keyword")
  public TopTenKeywordRes getPopularKeword(
      @RequestParam(value = "size", required = false, defaultValue = "10") @Min(1) @Max(50) Integer size
  ) {
    return blogSearchService.getPopularKeywordList();
  }

}
