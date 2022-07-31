package com.yanzhen.mapper;

import com.yanzhen.entity.ArticleView;

import java.util.List;
import java.util.Map;

public interface ArticleViewDao {

    int create(ArticleView articleView);

    int delete(Map<String,Object> paramMap);

    int update(Map<String,Object> paramMap);

    List<ArticleView> query(Map<String,Object> paramMap);

    ArticleView detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
