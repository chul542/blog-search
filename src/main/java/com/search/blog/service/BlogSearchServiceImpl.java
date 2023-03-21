package com.search.blog.service;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.KakaoBlogSearchApiDto.KakaoBlogSearchApiReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.BlogSearchWebDto.TopTenKeywordRes;
import com.search.blog.dto.web.NaverBlogSearchApiDto.NaverBlogSearchApiReq;
import com.search.blog.entity.BlogSearchHistoryEntity;
import com.search.blog.exception.custom.AllApiServerException;
import com.search.blog.repository.BlogSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

    try {
      return getKakaoBlogSearchList(blogSearchGetReq);
    } catch (Exception e) {
      log.info("Kakao Api Server makes error. Naver Api Server will handle the job.");
    }
    try {
      return getNaverBlogSearchList(blogSearchGetReq);
    } catch (Exception e) {
      log.info("Naver Api Server makes error.");
    }

    throw new AllApiServerException();
  }

  public TopTenKeywordRes getPopularKeywordList(int size) {
    int minSize = (int) Math.min(size, blogSearchRepository.count());
    Slice<BlogSearchHistoryEntity> searchHistoryEntities = blogSearchRepository.findAll(
        PageRequest.of(0, minSize, Sort.by(Direction.DESC, "numberOfSearch")));
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
        .sort(blogSearchGetReq.getSort().equals("recency") ? "date" : "sim" )
        .build()
    );
  }
}
