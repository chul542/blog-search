package com.search.blog.service;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import com.search.blog.dto.web.BlogSearchWebDto.TopTenKeywordRes;


public interface BlogSearchService {

  BlogSearchWebRes getBlogSearchList(BlogSearchGetReq blogSearchGetReq);

  TopTenKeywordRes getPopularKeywordList(int size);

}
