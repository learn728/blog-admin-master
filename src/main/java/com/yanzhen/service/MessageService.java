package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Message;
import com.yanzhen.entity.User;
import com.yanzhen.mapper.MessageDao;
import com.yanzhen.mapper.UserDao;
import com.yanzhen.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public int create(Message message) {
        return messageDao.create(message);
    }

    public int delete(Integer id) {
        return messageDao.delete(MapUtils.build().put("id", id).getMap());
    }

    public PageInfo<Message> query(Message message) {
        if (message != null && message.getPage() != null) {
            //startPage(当前页码, 每页显示大小)
            PageHelper.startPage(message.getPage(), message.getLimit());
        }
        //分页查询
        List<Message> list = messageDao.query(MapUtils.build().beanToMap(message));
        //将查询出来的数据使用PageInfo封装
        PageInfo<Message> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }



    //用户登录
//    public User login(String userName, String password) {
//        return userDao.detail(MapUtils.build()
//                .put("userName",userName)
//                .put("password",password)
//                .getMap());
//    }
//
//    public int count(User user) {
//        return userDao.count(MapUtils.build().beanToMap(user));
//    }
}
