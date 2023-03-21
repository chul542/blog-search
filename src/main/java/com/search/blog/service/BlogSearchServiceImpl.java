package com.search.blog.service;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.BlogSearchWebDto.TopTenKeywordRes;
import com.search.blog.dto.web.NaverBlogSearchApiDto.NaverBlogSearchApiReq;
import com.search.blog.entity.BlogSearchHistoryEntity;
import com.search.blog.repository.BlogSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogSearchServiceImpl implements BlogSearchService {

  private final ApiCallService apiCallService;
  private final BlogSearchRepository blogSearchRepository;

  @Transactional
  @Override
  public BlogSearchWebRes getBlogSearchList(BlogSearchGetReq blogSearchGetReq) {

    BlogSearchHistoryEntity blogSearchHistoryEntity = blogSearchRepository.findById(
        blogSearchGetReq.getQuery()).orElse(null);

    if (blogSearchHistoryEntity == null) {
      blogSearchRepository.save(
          BlogSearchHistoryEntity.builder()
              .keyword(blogSearchGetReq
                  .getQuery())
              .numberOfSearch(1L)
              .build());
    } else {
      blogSearchRepository.save(
          BlogSearchHistoryEntity.
              builder()
              .keyword(blogSearchHistoryEntity.getKeyword())
              .numberOfSearch(blogSearchHistoryEntity.getNumberOfSearch() + 1)
              .build());
    }

    return getNaverBlogSearchList(blogSearchGetReq);
  }

  public TopTenKeywordRes getPopularKeywordList() {
    int size = (int) Math.min(10, blogSearchRepository.count());
    Slice<BlogSearchHistoryEntity> searchHistoryEntities = blogSearchRepository.findAll(
        PageRequest.of(0, size, Sort.by(Direction.DESC, "numberOfSearch")));
    return TopTenKeywordRes.of(searchHistoryEntities);
  }

  private BlogSearchWebRes getKakaoBlogSearchList(BlogSearchGetReq blogSearchGetReq) {
    return apiCallService.getKakaoBlogSearchList(KakaoBlogSearchApiReq
        .builder()
        .query(blogSearchGetReq.getQuery())
        .sort(blogSearchGetReq.getSort())
        .page(blogSearchGetReq.getPage())
        .size(blogSearchGetReq.getSize())
        .build());
  }

  private BlogSearchWebRes getNaverBlogSearchList(BlogSearchGetReq blogSearchGetReq) {
    return apiCallService.getNaverBlogSearchList(NaverBlogSearchApiReq
        .builder()
        .query(blogSearchGetReq.getQuery())
        .display(blogSearchGetReq.getSize())
        .start(blogSearchGetReq.getPage() * blogSearchGetReq.getSize())
        .sort(blogSearchGetReq.getSort())
        .build()
    );
  }
}
