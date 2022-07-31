package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Channel;
import com.yanzhen.entity.Log;
import com.yanzhen.entity.User;
import com.yanzhen.service.ChannelService;
import com.yanzhen.service.LogService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private LogService logService;

    Log log =new Log();

    @PostMapping("/create")
    public Result create(@RequestBody Channel channel, HttpServletRequest request) {
        //从token中获取用户信息
        User user = (User) request.getAttribute("user");
        //设置创建人
        channel.setCreateUser(user.getId());
        channelService.create(channel);
        return Result.success(channel);
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        channelService.delete(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Channel channel) {
        channelService.update(channel);
        return Result.success(channel);
    }

    @PostMapping("/query")
    public Map<String, Object> query(@RequestBody Channel channel,HttpServletRequest request) {
        User user1 = (User)request.getAttribute("user");
        String s = user1.getUserName();
        log.setName(s);
        log.setAction("栏目管理");
        logService.create(log);
        PageInfo<Channel> pageInfo = channelService.query(channel);
        return Result.success(pageInfo);
    }

    @PostMapping("/tree")
    public Result tree() {
        //需要转换成树形结构。供前端使用并显示
        List<Channel> list = channelService.all();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Channel channel : list) {
            //首先获取顶级栏目
            if (channel.getParentId() == 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", channel.getId());
                map.put("label", channel.getName());
                List<Map<String, Object>> children = new ArrayList<>();
                for (Channel entity : list) {
                    if (entity.getParentId() == channel.getId()) {
                        Map<String, Object> subMap = new HashMap<>();
                        subMap.put("id", entity.getId());
                        subMap.put("label", entity.getName());
                        children.add(subMap);
                    }
                }
                map.put("children", children);
                mapList.add(map);
            }
        }
        return Result.success(mapList);
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        Channel channel = channelService.detail(id);
        return Result.success(channel);
    }

    @PostMapping("/count")
    public Result count(@RequestBody Channel channel) {
        int count = channelService.count(channel);
        return new Result(count);
    }
}
