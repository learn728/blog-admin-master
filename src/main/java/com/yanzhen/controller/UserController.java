package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Article;
import com.yanzhen.entity.Collect;
import com.yanzhen.entity.Log;
import com.yanzhen.entity.User;
import com.yanzhen.exception.MyException;
import com.yanzhen.service.LogService;
import com.yanzhen.service.UserService;
import com.yanzhen.utils.JWTUtil;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;


    Log log =new Log();
/*管理员用户注册*/
    @PostMapping("/create")
    public Result create(@RequestBody User user) {
        userService.create(user);
        return Result.success(user);
    }
/*普通用户注册*/
    @PostMapping("/create1")
    public Result create1(@RequestBody User user) {
        userService.create1(user);
        return Result.success(user);
    }
/*收藏,要登录权限*/
    @PostMapping("/create2")
    public Result create2(Integer id,HttpServletRequest request) {
        User user1 = (User)request.getAttribute("user");
        Collect collect = new Collect();
        Integer id1 = user1.getId();
        collect.setArticleId(id);
        collect.setUserId(id1);
        userService.create2(collect);
        return Result.success();
    }
/*查询是否收藏*/
    @PostMapping("/query1")
    public Result query1(Integer id,HttpServletRequest request) {
        User user1 = (User)request.getAttribute("user");
        Collect collect = new Collect();
        Integer id1 = user1.getId();
        collect.setArticleId(id);
        collect.setUserId(id1);
        if(userService.query1(collect) == null){
            return Result.fail("没收藏");
        }
        Collect collect1 = userService.query1(collect);
        return Result.success(collect1);
    }
/*取消收藏*/
    @PostMapping("/delete1")
    public Result delete1(Integer id) {
        userService.delete1(id);
        return Result.success();
    }

    /*查询收藏的文章*/
    @PostMapping("/findTitle")
    public Result findTitle(HttpServletRequest request) {
//        System.out.println("访问到了");
        User user = (User)request.getAttribute("user");
        Integer id = user.getId();
        List<Article> title = userService.findTitle(id);
        return Result.success(title);
    }
/*是否已经登录*/
    @PostMapping("/isLogin")
    public Result isLogin(HttpServletRequest request) {
//        System.out.println("访问到了");
        String token = request.getHeader("token");
        User user = JWTUtil.getUser1(token);
//        System.out.println(user);
        if(user == null) {
             return Result.fail();
        }

        return Result.success();
    }



    @PostMapping("/delete")
    public Result delete(Integer id) {
        userService.delete(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
//        System.out.println(user);
        userService.update(user);
        return Result.success(user);
    }

    /*只修改密码*/
    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody User user) {
        user.setId(2);
        userService.updatePwd(user);
        return Result.success(user);
    }

    @PostMapping("/query")
    public Map<String,Object> query(@RequestBody User user, HttpServletRequest request) {
        User user1 = (User)request.getAttribute("user");
        String s = user1.getUserName();
        log.setName(s);
        log.setAction("用户管理");
        logService.create(log);

//        System.out.println(user1);
        PageInfo<User> pageInfo = userService.query(user);

        return Result.success(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        User user = userService.detail(id);
        return Result.success(user);
    }

    @PostMapping("/count")
    public Result count(@RequestBody User user) {
        int count = userService.count(user);
        return new Result(count);
    }
}
