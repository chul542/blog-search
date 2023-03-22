package com.search.blog.service;

import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.NaverBlogSearchApiDto.NaverBlogSearchApiReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiCallServiceTest {

  @Autowired
  private ApiCallService apiCallService;

  @Test
  @DisplayName("Kakao Api Server Test")
  void getKakaoBlogSearchListTest() {
    BlogSearchWebRes blogSearchWebRes =
        apiCallService.getKakaoBlogSearchList(
            KakaoBlogSearchApiReq.builder().query("develop").sort("accuracy")
                .page(10).size(10).build()
        );
    Assertions.assertEquals(blogSearchWebRes.getMeta().getPage(), 10);
  }

  @Test
  @DisplayName("Naver Api Server Test")
  void getNaverBlogSearchListTest() {
    BlogSearchWebRes blogSearchWebRes =
        apiCallService.getNaverBlogSearchList(
            NaverBlogSearchApiReq.builder().query("develop").sort("date")
                .start(1).display(10).build()
        );
    Assertions.assertEquals(blogSearchWebRes.getMeta().getDisplay(), 10);

  }


}
