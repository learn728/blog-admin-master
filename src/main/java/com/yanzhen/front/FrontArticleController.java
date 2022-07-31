package com.yanzhen.front;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Article;
import com.yanzhen.service.ArticleService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/f/article")
public class FrontArticleController {

    @Autowired
    private ArticleService articleService;

    //查询单个文章
    @GetMapping("/get")
    public Result getById(Integer id){
        Article detail = articleService.detail(id);
        return Result.success(detail);
    }

    //前台分页查询所有已发布的博客
    @PostMapping("/getList1")
    public Map getByChannelId1(@RequestBody Article article){

        PageInfo<Article> page = articleService.query1(article);
        return Result.success(page);
    }

    /*前端条件查询博客*/
    @PostMapping("/getSer")
    public Map getBySer(@RequestBody Article article){

        PageInfo<Article> page = articleService.query2(article);
        return Result.success(page);
    }

    @GetMapping("/getList")
    public Map getByChannelId(Article article){

        PageInfo<Article> page = articleService.query(article);
        return Result.success(page);
    }

    //文章排行
    @GetMapping("/getList2")
    public Map getByChannelId2(Article article){
        PageInfo<Article> page = articleService.queryOrder(article);
        return Result.success(page);
    }

    //获取前几条数据
    @GetMapping("/getTop")
    public Result getById(Article article, Integer top){
        List<Article> list = articleService.top(article, top);
        return Result.success(list);
    }

    //获取轮播博客
    @GetMapping("/getLb")
    public Map getByLb(Article article){
        PageInfo<Article> page = articleService.queryLb(article);
        return Result.success(page);
    }

    //获取置顶的博客
    @GetMapping("/getUp")
    public Map getUp(Article article){

        PageInfo<Article> page = articleService.queryTop(article);
//        System.out.println(page);
        return Result.success(page);
    }
}
