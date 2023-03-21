package com.search.blog.controller;

import static com.search.blog.config.MvcConfig.BLOG_SEARCH_URI;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.BlogSearchWebDto.TopTenKeywordRes;
import com.search.blog.exception.custom.PageLimitException;
import com.search.blog.service.BlogSearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = BLOG_SEARCH_URI)
@Validated
public class BlogSearchController {

  private final BlogSearchService blogSearchService;

  @ApiOperation(value = "Ping-Pong Api", notes = "This is server test api")
  @GetMapping(path = "/test")
  public String testFunc() {
    return "Ping! Pong!";
  }

  @ApiOperation(value = "Search Blog Using Keyword", notes = "It returns the blog information "
      + "from the Kakao Api server. If it triggers the error, it returns the result from the Naver "
      + "Api server.")
  @GetMapping()
  public BlogSearchWebRes getBlogSearchList(
      @RequestParam(value = "query", required = true)
          @NotNull(message = "Search query must not be null")
          String query,
      @Email
      @RequestParam(value = "sort", required = false, defaultValue = "accuracy")
          String sort,
      @RequestParam(value = "page", required = false, defaultValue = "1")
      @Min(value = 1, message = "page must be grater than or equal to 1")
      @Max(value = 50, message = "page must be less than or equal to 50")
          Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10")
      @Min(value=1,message = "size must be grater than or equal to 1" )
      @Max(value = 50, message = "size must be less than or equal to 50")
          Integer size
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
      @RequestParam(value = "size", required = false, defaultValue = "10") @Min(1) @Max(15) int size
  ) {
    return blogSearchService.getPopularKeywordList(size);
  }

}
