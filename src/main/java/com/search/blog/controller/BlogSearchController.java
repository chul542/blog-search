package com.search.blog.controller;

import static com.search.blog.config.MvcConfig.BLOG_SEARCH_URI;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = BLOG_SEARCH_URI)
public class BlogSearchController {

}
