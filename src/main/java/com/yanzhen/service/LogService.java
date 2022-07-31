package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import com.yanzhen.entity.Cuser;
import com.yanzhen.entity.Log;
import com.yanzhen.entity.User;
//import com.yanzhen.mapper.CuserDao;
import com.yanzhen.mapper.LogDao;
import com.yanzhen.mapper.UserDao;
import com.yanzhen.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogDao logDao;

    public int create(Log log) {
        return logDao .create(log);
    }

    public int delete(Integer id) {
        return logDao.delete(MapUtils.build().put("id", id).getMap());
    }


    public PageInfo<Log> query(Log log) {
        if (log != null && log.getPage() != null) {
            //startPage(当前页码, 每页显示大小)
            PageHelper.startPage(log.getPage(), log.getLimit());
        }
        //分页查询
        List<Log> list = logDao.query(MapUtils.build().beanToMap(log));
        //将查询出来的数据使用PageInfo封装
//        System.out.println(list);
        PageInfo<Log> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
//
//    public User detail(Integer id) {
//        return cuserDao.detail(MapUtils.build().put("id", id).getMap());
//    }
//
//    //用户登录
//    public User login(String userName, String password) {
//        return cuserDao.detail(MapUtils.build()
//                .put("userName",userName)
//                .put("password",password)
//                .getMap());
//    }
//
//    public int count(User user) {
//        return cuserDao.count(MapUtils.build().beanToMap(user));
//    }
}
