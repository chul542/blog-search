package com.search.blog.controller;

import static com.search.blog.config.MvcConfig.BLOG_SEARCH_URI;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.service.BlogSearchService;
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

  @GetMapping(path="/test")
  public String testFunc() {
    return "asdasdsa";
  }

  @GetMapping()
  public BlogSearchWebRes getBlogSearchList(
      @RequestParam(value = "query") String query,
      @RequestParam(value = "sort", required = false, defaultValue = "accuracy")
      @Pattern(regexp = "accuracy|recency", message = "Invalid query parameter value. "
          + "Allowed values are 'accuracy' and 'recency'.") String sort,
      @RequestParam(value = "page", required = false, defaultValue = "1") @Min(1) @Max(50) Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") @Min(1) @Max(50) Integer size
  ) {
    System.out.println(query);
    System.out.println(sort);
    System.out.println(page);
    System.out.println(size);
    return blogSearchService.getBlogSearchList(BlogSearchGetReq
        .builder()
        .query(query)
        .sort(sort)
        .page(page)
        .size(size)
        .build());
  }

}
