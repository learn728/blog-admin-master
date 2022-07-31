package com.yanzhen.mapper;

import com.yanzhen.entity.FriendLink;

import java.util.List;
import java.util.Map;

public interface FriendLinkDao {

    int create(FriendLink friendLink);

    int delete(Map<String,Object> paramMap);

    int update(Map<String,Object> paramMap);

    List<FriendLink> query(Map<String,Object> paramMap);

    FriendLink detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
