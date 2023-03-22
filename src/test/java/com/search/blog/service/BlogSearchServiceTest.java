package com.search.blog.service;

import static org.mockito.BDDMockito.given;

import com.search.blog.entity.BlogSearchHistoryEntity;
import com.search.blog.repository.BlogSearchRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class BlogSearchServiceTest {

  @Mock
  BlogSearchRepository blogSearchRepository;

  @InjectMocks
  private BlogSearchServiceImpl blogSearchServiceImpl;

  @Test
  @DisplayName("service test")
  void getPopularKeywordListTest() {
    BlogSearchHistoryEntity blogSearchHistoryEntity =
        BlogSearchHistoryEntity
            .builder()
            .keyword("develop")
            .numberOfSearch(20L)
            .build();

    List<BlogSearchHistoryEntity> entities = new ArrayList<>();
    entities.add(blogSearchHistoryEntity);

    given(blogSearchRepository.findAll()).willReturn(entities);

    List<BlogSearchHistoryEntity> findEntities = blogSearchRepository.findAll();
    Assertions.assertEquals(blogSearchHistoryEntity.getKeyword(), findEntities.get(0).getKeyword());
  }


}
