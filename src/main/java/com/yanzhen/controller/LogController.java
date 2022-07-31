package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Article;
import com.yanzhen.entity.Log;
import com.yanzhen.service.ArticleService;
import com.yanzhen.service.LogService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;



    @PostMapping("/delete")
    public Result delete(Integer id) {
        logService.delete(id);
        return Result.success();
    }



    @PostMapping("/query")
    public Map<String,Object> query(@RequestBody Log log) {
//        System.out.println(log);
        PageInfo<Log> pageInfo = logService.query(log);
        return Result.success(pageInfo);
    }

//    @PostMapping("/detail")
//    public Result detail(Integer id) {
//        Article article = articleService.detail(id);
//        return Result.success(article);
//    }
//
//    @PostMapping("/count")
//    public Result count(@RequestBody Log log) {
//        int count = logService.count(log);
//        return new Result(count);
//    }
}
