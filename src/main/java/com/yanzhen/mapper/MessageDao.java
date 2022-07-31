package com.yanzhen.mapper;

import com.yanzhen.entity.Log;
import com.yanzhen.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageDao {

    int create(Message message);
    List<Message> query(Map<String,Object> paramMap);

    int delete(Map<String,Object> paramMap);


}
