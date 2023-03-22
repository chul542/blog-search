package com.search.blog.conroller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogSearchControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Blog Search List Get TEST")
  void getBlogSearchListTest() throws Exception {

    mockMvc.perform(get("/v1/search/blog?query=develop")).andExpect(status().isOk());

  }

  @Test
  @DisplayName("Top Ten Popular Search List GET TEST")
  void getPopularKeywordListList() throws Exception {

    mockMvc.perform(get("/v1/search/blog/popular-keyword")).andExpect(status().isOk());

  }
}
