package com.search.blog.controller;

import static com.search.blog.config.MvcConfig.BLOG_SEARCH_URI;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.BlogSearchWebDto.PopularKeywordRes;
import com.search.blog.exception.custom.PageLimitException;
import com.search.blog.service.BlogSearchService;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

  @ApiOperation(value = "This function searches for blogs using a keyword and returns the "
      + "corresponding blog information from the Kakao API server. If an error occurs, "
      + "the function will return the result from the Naver API server instead")
  @GetMapping()
  public BlogSearchWebRes getBlogSearchList(
      @RequestParam(value = "query", required = true)
          @NotNull(message = "Search query must not be null")
          String query,
      @RequestParam(value = "sort", required = false, defaultValue = "accuracy")
      @Pattern(regexp = "^(accuracy|recency|\\s*)$", message = "for `sort`, only three options "
          + "are allowed 'accuracy', 'recency', or null")
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

  @ApiOperation(value = "This function returns the top ten popular keywords by default, "
      + "but the number of keywords returned can be adjusted from 1 to 20. If the number "
      + "of saved keywords in the database is lower than the requested number of popular keywords,"
      + " it will return all available keywords.")
  @GetMapping(path = "/popular-keyword")
  public PopularKeywordRes getPopularKeword(
      @RequestParam(value = "size", required = false, defaultValue = "10") int size
  ) {
    return blogSearchService.getPopularKeywordList(size);
  }

}
