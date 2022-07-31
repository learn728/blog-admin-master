package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.ArticleView;
import com.yanzhen.service.ArticleViewService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/articleView")
public class ArticleViewController {

    @Autowired
    private ArticleViewService articleViewService;

    @PostMapping("/create")
    public Result create(@RequestBody ArticleView articleView) {
        articleViewService.create(articleView);
        return Result.success(articleView);
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        articleViewService.delete(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ArticleView articleView) {
        articleViewService.update(articleView);
        return Result.success(articleView);
    }

    @PostMapping("/query")
    public Map<String,Object> query(@RequestBody ArticleView articleView) {
        PageInfo<ArticleView> pageInfo = articleViewService.query(articleView);
        return Result.success(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        ArticleView articleView = articleViewService.detail(id);
        return Result.success(articleView);
    }

    @PostMapping("/count")
    public Result count(@RequestBody ArticleView articleView) {
        int count = articleViewService.count(articleView);
        return new Result(count);
    }
}
