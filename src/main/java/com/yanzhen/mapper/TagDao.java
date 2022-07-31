package com.yanzhen.mapper;

import com.yanzhen.entity.Tag;

import java.util.List;
import java.util.Map;

public interface TagDao {

    int create(Tag tag);

    int delete(Map<String,Object> paramMap);

    int update(Map<String,Object> paramMap);

    List<Tag> query(Map<String,Object> paramMap);

    Tag detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
