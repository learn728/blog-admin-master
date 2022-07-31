package com.yanzhen.mapper;

import com.yanzhen.entity.Log;

import java.util.List;
import java.util.Map;

public interface LogDao {

    int create(Log log);        //新建会返回int类型

    List<Log> query(Map<String,Object> paramMap);

    int delete(Map<String,Object> paramMap);
//
//    int update(Map<String,Object> paramMap);
//
//
//
//    User detail(Map<String,Object> paramMap);
//
//    int count(Map<String,Object> paramMap);
}
