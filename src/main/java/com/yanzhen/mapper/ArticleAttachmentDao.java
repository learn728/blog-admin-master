package com.yanzhen.mapper;

import com.yanzhen.entity.ArticleAttachment;

import java.util.List;
import java.util.Map;

public interface ArticleAttachmentDao {

    int create(ArticleAttachment articleAttachment);

    int delete(Map<String,Object> paramMap);

    int update(Map<String,Object> paramMap);

    List<ArticleAttachment> query(Map<String,Object> paramMap);

    ArticleAttachment detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
