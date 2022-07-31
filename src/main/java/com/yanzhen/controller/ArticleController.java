package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Article;
import com.yanzhen.entity.Log;
import com.yanzhen.entity.User;
import com.yanzhen.service.ArticleService;
import com.yanzhen.service.LogService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController//包含@Responsebody
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LogService logService;

    Log log =new Log();

    @PostMapping("/create")
    public Result create(@RequestBody Article article) {
        System.out.println(article);
        articleService.create(article);
        return Result.success(article);
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        articleService.delete(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Article article) {
        articleService.update(article);
        return Result.success(article);
    }

    /*文章管理模块*/
    @PostMapping("/query")
    public Map<String,Object> query(@RequestBody Article article, HttpServletRequest request) {//当前页属性拿到了
        User user1 = (User)request.getAttribute("user");
        String s = user1.getUserName();
        log.setName(s);
        log.setAction("文章管理");
        logService.create(log);

        PageInfo<Article> pageInfo = articleService.query2(article);
        return Result.success(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        Article article = articleService.detail(id);
        return Result.success(article);
    }

    @PostMapping("/count")
    public Result count(@RequestBody Article article) {
        int count = articleService.count(article);
        return new Result(count);
    }
}
