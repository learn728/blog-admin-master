package com.yanzhen.mapper;

import com.yanzhen.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface ArticleDao {

    int create(Article article);

    int delete(Map<String,Object> paramMap);

    int update(Map<String,Object> paramMap);

    /*正常查询博客*/
    List<Article> query(Map<String,Object> paramMap);

    //前台分页查询已发布的博客

    List<Article> query1(Map<String,Object> paramMap);

    //查询博客，随机排列
    List<Article> queryRand(Map<String,Object> paramMap);

    //查询顶置博客
    List<Article> queryTop(Map<String,Object> paramMap);

    //查询轮播博客
    List<Article> queryLb(Map<String,Object> paramMap);

    List<Article> queryOrder(Map<String,Object> paramMap);

    Article detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
