package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.FriendLink;
import com.yanzhen.entity.Log;
import com.yanzhen.entity.User;
import com.yanzhen.service.FriendLinkService;
import com.yanzhen.service.LogService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/friendLink")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private LogService logService;

    Log log =new Log();

    @PostMapping("/create")
    public Result create(@RequestBody FriendLink friendLink) {
        friendLinkService.create(friendLink);
        return Result.success(friendLink);
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        friendLinkService.delete(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody FriendLink friendLink) {
        friendLinkService.update(friendLink);
        return Result.success(friendLink);
    }

    @PostMapping("/query")
    public Map<String,Object> query(@RequestBody FriendLink friendLink, HttpServletRequest request) {
        User user1 = (User)request.getAttribute("user");
        String s = user1.getUserName();
        log.setName(s);
        log.setAction("链接管理");
        logService.create(log);
        PageInfo<FriendLink> pageInfo = friendLinkService.query(friendLink);
        return Result.success(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        FriendLink friendLink = friendLinkService.detail(id);
        return Result.success(friendLink);
    }

    @PostMapping("/count")
    public Result count(@RequestBody FriendLink friendLink) {
        int count = friendLinkService.count(friendLink);
        return new Result(count);
    }
}
