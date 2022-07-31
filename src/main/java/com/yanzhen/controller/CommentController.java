package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Comment;
import com.yanzhen.entity.CommentVo;
import com.yanzhen.entity.Log;
import com.yanzhen.entity.User;
import com.yanzhen.service.CommentService;
import com.yanzhen.service.LogService;
import com.yanzhen.utils.JWTUtil;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private LogService logService;

    Log log =new Log();
/*存评论*/
    @PostMapping("/create")
    public Result create(@RequestBody Comment comment,HttpServletRequest request) {
        String token = request.getHeader("token");
        User user = JWTUtil.getUser(token);
        Integer id1 = user.getId();
        comment.setUserId(id1);

        commentService.create(comment);
        return Result.success(comment);
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        commentService.delete(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Comment comment) {
        commentService.update(comment);
        return Result.success(comment);
    }
//查询所有评论
    @PostMapping("/query")
    public Map<String,Object> query(@RequestBody Comment comment, HttpServletRequest request) {
        User user1 = (User)request.getAttribute("user");
        String s = user1.getUserName();
        log.setName(s);
        log.setAction("评论管理");
        logService.create(log);
        PageInfo<CommentVo> pageInfo = commentService.query(comment);
        return Result.success(pageInfo);
    }

/*查询某篇文章所有评论*/
    @PostMapping("/query2")
    public Result query1(Integer id) {
        List<CommentVo> comment = commentService.query1(id);
        return Result.success(comment);
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        Comment comment = commentService.detail(id);
        return Result.success(comment);
    }

    @PostMapping("/count")
    public Result count(@RequestBody Comment comment) {
        int count = commentService.count(comment);
        return new Result(count);
    }
}
