package com.yanzhen.mapper;

import com.yanzhen.entity.ArticleTag;

import java.util.List;
import java.util.Map;

public interface ArticleTagDao {

    int create(ArticleTag articleTag);

    int delete(Map<String,Object> paramMap);

    int update(Map<String,Object> paramMap);

    List<ArticleTag> query(Map<String,Object> paramMap);

    ArticleTag detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
