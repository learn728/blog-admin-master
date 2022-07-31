package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Log;
import com.yanzhen.entity.Tag;
import com.yanzhen.entity.User;
import com.yanzhen.service.LogService;
import com.yanzhen.service.TagService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private LogService logService;

    Log log =new Log();

    @PostMapping("/create")
    public Result create(@RequestBody Tag tag) {
        tagService.create(tag);
        return Result.success(tag);
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        tagService.delete(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Tag tag) {
        tagService.update(tag);
        return Result.success(tag);
    }

    @PostMapping("/query")
    public Map<String,Object> query(@RequestBody Tag tag, HttpServletRequest request) {
        User user1 = (User)request.getAttribute("user");
        String s = user1.getUserName();
        log.setName(s);
        log.setAction("标签管理");
        logService.create(log);
        PageInfo<Tag> pageInfo = tagService.query(tag);
        return Result.success(pageInfo);
    }

    @PostMapping("/all")
    public Result all() {
        List<Tag> list = tagService.all();
        return Result.success(list);
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        Tag tag = tagService.detail(id);
        return Result.success(tag);
    }

    @PostMapping("/count")
    public Result count(@RequestBody Tag tag) {
        int count = tagService.count(tag);
        return new Result(count);
    }
}
