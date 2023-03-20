package com.search.blog.service;

import com.search.blog.dto.service.BlogSearchServiceDto.BlogSearchGetReq;
import com.search.blog.dto.web.BlogSearchWebDto.BlogSearchWebRes;
import org.springframework.stereotype.Service;


public interface BlogSearchService {

  BlogSearchWebRes getBlogSearchList(BlogSearchGetReq blogSearchGetReq);

}
