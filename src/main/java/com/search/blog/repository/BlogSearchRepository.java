package com.search.blog.repository;

import com.search.blog.entity.BlogSearchHistoryEntity;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogSearchRepository extends JpaRepository<BlogSearchHistoryEntity, String> {


}
