package com.yanzhen.controller;

import com.yanzhen.entity.Log;
import com.yanzhen.entity.User;
import com.yanzhen.exception.MyException;
import com.yanzhen.service.LogService;
import com.yanzhen.service.UserService;
import com.yanzhen.utils.JWTUtil;
import com.yanzhen.utils.MapUtils;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

   Log log =new Log();
//管理员用户登录
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {
        String userName = map.get("userName");
        String password = map.get("password");

        if (userService.login(userName,password) ==null){//解决查询为空报异常
            return Result.fail("用户名或密码错误");        }

        User user = userService.login(userName, password);
//        System.out.println(user+"fjgdu");
        String s = user.getUserName();
//        System.out.println(user);
        if (user != null) {
            //生成token
            String token = JWTUtil.sign(user);
//            System.out.println(JWTUtil.getUser(token));
            //响应数据                                创建MapUtils对象
            Map<String, Object> loginMap = MapUtils.build().put("token", token).put("user", user).getMap();

            log.setName(s);
            log.setAction("登录");
            logService.create(log);

            return Result.success(loginMap);
        } else {
//            throw new MyException("用户名或密码错误");
            return Result.fail("用户名或密码错误");
        }
    }

    @PostMapping("/login1")
    public Result login1(@RequestBody Map<String, String> map) {
        String userName = map.get("userName");
        String password = map.get("password");

        if (userService.login1(userName,password) ==null){//解决查询为空报异常
            return Result.fail("用户名或密码错误");        }

        User user = userService.login1(userName, password);
//        System.out.println(user+"fjgdu");
        String s = user.getUserName();
//        System.out.println(user);
        if (user != null) {
            //生成token
            String token = JWTUtil.sign(user);
//            System.out.println(JWTUtil.getUser(token));
            //响应数据                                创建MapUtils对象
            Map<String, Object> loginMap = MapUtils.build().put("token", token).put("user", user).getMap();

            log.setName(s);
            log.setAction("登录");
            logService.create(log);

            return Result.success(loginMap);
        } else {
//            throw new MyException("用户名或密码错误");
            return Result.fail("用户名或密码错误");
        }
    }
}
