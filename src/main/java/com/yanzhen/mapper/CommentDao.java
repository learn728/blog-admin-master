package com.yanzhen.mapper;

import com.yanzhen.entity.Comment;
import com.yanzhen.entity.CommentVo;

import java.util.List;
import java.util.Map;

public interface CommentDao {

    int create(Comment comment);

    int delete(Map<String,Object> paramMap);

    int update(Map<String,Object> paramMap);

    List<CommentVo> query(Map<String,Object> paramMap);

    Comment detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);

    List<CommentVo> query1(int id);
}
