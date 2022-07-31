package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.ArticleTag;
import com.yanzhen.service.ArticleTagService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/articleTag")
public class ArticleTagController {

    @Autowired
    private ArticleTagService articleTagService;

    @PostMapping("/create")
    public Result create(@RequestBody ArticleTag articleTag) {
        articleTagService.create(articleTag);
        return Result.success(articleTag);
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        articleTagService.delete(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ArticleTag articleTag) {
        articleTagService.update(articleTag);
        return Result.success(articleTag);
    }

    @PostMapping("/query")
    public Map<String,Object> query(@RequestBody ArticleTag articleTag) {
        PageInfo<ArticleTag> pageInfo = articleTagService.query(articleTag);
        return Result.success(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        ArticleTag articleTag = articleTagService.detail(id);
        return Result.success(articleTag);
    }

    @PostMapping("/count")
    public Result count(@RequestBody ArticleTag articleTag) {
        int count = articleTagService.count(articleTag);
        return new Result(count);
    }
}
