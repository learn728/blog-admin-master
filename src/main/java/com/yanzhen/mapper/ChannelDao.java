package com.yanzhen.mapper;

import com.yanzhen.entity.Channel;

import java.util.List;
import java.util.Map;

public interface ChannelDao {

    int create(Channel channel);

    int delete(Map<String,Object> paramMap);

    int update(Map<String,Object> paramMap);

    List<Channel> query(Map<String,Object> paramMap);

    Channel detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
