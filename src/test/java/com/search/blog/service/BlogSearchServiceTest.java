package com.search.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.BlogSearchWebDto.TopTenKeywordRes;
import com.search.blog.repository.BlogSearchRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogSearchServiceTest {

  @Autowired
  BlogSearchRepository blogSearchRepository;

  @Autowired
  private BlogSearchService blogSearchService;


  @Test
  @DisplayName("Blog Search Service Test")
  void getBlogSearchListTest() {

    BlogSearchWebRes blogSearchWebRes = blogSearchService.getBlogSearchList(BlogSearchGetReq.
        builder()
        .query("develop")
        .size(10)
        .page(10)
        .sort("accuracy")
        .build()
    );
    assertThat(blogSearchWebRes.getDocuments()).hasSize(10);

  }

  @Test
  @DisplayName("Popular Keyword Service Test")
  void getPopularKeywordListTest() {

    TopTenKeywordRes topTenKeywordRes = blogSearchService.getPopularKeywordList(1);
    assertThat(topTenKeywordRes.getKeywordList()).hasSize(1);

  }
}
