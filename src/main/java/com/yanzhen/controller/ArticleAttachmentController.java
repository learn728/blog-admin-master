package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.ArticleAttachment;
import com.yanzhen.service.ArticleAttachmentService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/articleAttachment")
public class ArticleAttachmentController {

    @Autowired
    private ArticleAttachmentService articleAttachmentService;

    @PostMapping("/create")
    public Result create(@RequestBody ArticleAttachment articleAttachment) {
        articleAttachmentService.create(articleAttachment);
        return Result.success(articleAttachment);
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        articleAttachmentService.delete(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ArticleAttachment articleAttachment) {
        articleAttachmentService.update(articleAttachment);
        return Result.success(articleAttachment);
    }

    @PostMapping("/query")
    public Map<String,Object> query(@RequestBody ArticleAttachment articleAttachment) {
        PageInfo<ArticleAttachment> pageInfo = articleAttachmentService.query(articleAttachment);
        return Result.success(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        ArticleAttachment articleAttachment = articleAttachmentService.detail(id);
        return Result.success(articleAttachment);
    }

    @PostMapping("/count")
    public Result count(@RequestBody ArticleAttachment articleAttachment) {
        int count = articleAttachmentService.count(articleAttachment);
        return new Result(count);
    }
}
