package com.search.blog.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.search.blog.entity.BlogSearchHistoryEntity;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BlogSearchRepositoryTest {

  @Autowired
  private BlogSearchRepository blogSearchRepository;

  @Test
  @DisplayName("BlogSearchRepositoryTest.saveAndFind()")
  void saveAndFindTest() {

    String keyword = "Developer";
    Long numberOfSearch = 3L;

    blogSearchRepository.save(BlogSearchHistoryEntity
        .builder()
        .keyword(keyword)
        .numberOfSearch(numberOfSearch)
        .build());

    Optional<BlogSearchHistoryEntity> blogSearchHistoryEntity = blogSearchRepository.findById(keyword);

    assertThat(blogSearchHistoryEntity.get().getNumberOfSearch()).isEqualTo(numberOfSearch);

  }

}
