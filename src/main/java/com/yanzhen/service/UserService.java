package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Article;
import com.yanzhen.entity.Collect;
import com.yanzhen.entity.User;
import com.yanzhen.mapper.UserDao;
import com.yanzhen.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;



    public int create(User user) {
       user.setType1(1);//设置为管理员
        return userDao.create(user);
    }

    public int create1(User user) {
        user.setType1(9);//设置普通用户
        return userDao.create(user);
    }
/*新建收藏*/
    public int create2(Collect collect) {
        return userDao.create2(collect);
    }

    /*查询是否收藏*/
    public Collect query1(Collect collect) {
       Collect list = userDao.query1(collect);
        System.out.println(list);
        return list;
    }

    public List<Article> findTitle(int id) {
        System.out.println(id);
        List<Article> title = userDao.findTitle(id);
        System.out.println(title);
        return title;
    }



    public int delete(Integer id) {
        return userDao.delete(MapUtils.build().put("id", id).getMap());
    }
    public int delete1(Integer id) {
        return userDao.delete1(MapUtils.build().put("id", id).getMap());
    }

    public int update(User user) {
        return userDao.update(MapUtils.build().put("id", user.getId()).beanToMapForUpdate(user));
    }

    public int updatePwd(User user) {
        return userDao.updatePwd(user);
    }

    public PageInfo<User> query(User user) {
        if (user != null && user.getPage() != null) {
            //startPage(当前页码, 每页显示大小)
            PageHelper.startPage(user.getPage(), user.getLimit());
        }
        //分页查询
        List<User> list = userDao.query(MapUtils.build().beanToMap(user));
        //将查询出来的数据使用PageInfo封装
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public User detail(Integer id) {
        return userDao.detail(MapUtils.build().put("id", id).getMap());
    }

    //管理员用户登录
    public User login(String userName, String password) {
        return userDao.login(MapUtils.build()
                .put("userName",userName)
                .put("password",password)
                .getMap());
    }

    public User login1(String userName, String password) {
        return userDao.login1(MapUtils.build()
                .put("userName",userName)
                .put("password",password)
                .getMap());
    }

    public int count(User user) {
        return userDao.count(MapUtils.build().beanToMap(user));
    }}


