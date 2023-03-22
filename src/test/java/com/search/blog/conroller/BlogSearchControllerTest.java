package com.search.blog.conroller;

import static com.search.blog.config.MvcConfig.BLOG_SEARCH_URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.doReturn;

import com.google.gson.Gson;
import com.search.blog.controller.BlogSearchController;
import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.BlogSearchWebDto.TopTenKeywordRes;
import com.search.blog.dto.web.NaverBlogSearchApiDto.NaverBlogSearchApiReq;
import com.search.blog.service.ApiCallService;
import com.search.blog.service.ApiCallServiceImpl;
import com.search.blog.service.BlogSearchService;
import com.search.blog.service.BlogSearchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
class BlogSearchControllerTest {

  @InjectMocks
  private BlogSearchController blogSearchController;

  @Mock
  private BlogSearchServiceImpl blogSearchServiceImpl;

  @Mock
  private ApiCallServiceImpl apiCallServiceImpl;


  MockMvc mockMvc;

  @BeforeEach
  public void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(blogSearchController).build();
  }

  @Test
  @DisplayName("Blog Search List Get")
  void getBlogSearchListTest() throws Exception {

    mockMvc.perform(get("/v1/search/blog?query=develop")).andExpect(status().isOk());

  }

  @Test
  @DisplayName("Top Ten Popular Search List Test")
  void getPopularKeywordListList() throws Exception {

    mockMvc.perform(get("/v1/search/blog/popular-keyword")).andExpect(status().isOk());

  }

}
