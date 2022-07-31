package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Log;
import com.yanzhen.entity.Message;
import com.yanzhen.entity.User;
import com.yanzhen.service.LogService;
import com.yanzhen.service.MessageService;
import com.yanzhen.service.UserService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

//    @Autowired
//    private LogService logService;
//
//    Log log =new Log();

    @PostMapping("/create")
    public Result create(@RequestBody Message message) {
        messageService.create(message);
        return Result.success(message);
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        messageService.delete(id);
        return Result.success();
    }

    @PostMapping("/query")
    public Map<String,Object> query(@RequestBody Message message, HttpServletRequest request) {
//        User user1 = (User)request.getAttribute("user");
//        String s = user1.getUserName();
//        log.setName(s);
//        log.setAction("留言管理");
//        logService.create(log);
//        System.out.println(log);
        PageInfo<Message> pageInfo = messageService.query(message);
        return Result.success(pageInfo);
    }


}
