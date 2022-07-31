package com.yanzhen.mapper;

import com.yanzhen.entity.Article;
import com.yanzhen.entity.Collect;
import com.yanzhen.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    int create(User user);

    int create2(Collect collect);

    Collect query1(Collect collect);

    List<Article> findTitle(int id);//查询收藏的文章

    int delete(Map<String,Object> paramMap);

    int delete1(Map<String,Object> paramMap);

    int update(Map<String,Object> paramMap);

    int updatePwd(User user);

    List<User> query(Map<String,Object> paramMap);

    User login(Map<String,Object> paramMap);

    User login1(Map<String,Object> paramMap);
    User detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
