package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Comment;
import com.yanzhen.entity.CommentVo;
import com.yanzhen.mapper.CommentDao;
import com.yanzhen.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public int create(Comment comment) {
        return commentDao.create(comment);
    }

    public int delete(Integer id) {
        return commentDao.delete(MapUtils.build().put("id", id).getMap());
    }

    public int update(Comment comment) {
        return commentDao.update(MapUtils.build().put("id", comment.getId()).beanToMapForUpdate(comment));
    }

    public PageInfo<CommentVo> query(Comment comment) {
        if (comment != null && comment.getPage() != null) {
            PageHelper.startPage(comment.getPage(), comment.getLimit());
        }
        List<CommentVo> list = commentDao.query(MapUtils.build().beanToMap(comment));
        PageInfo<CommentVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public Comment detail(Integer id) {
        return commentDao.detail(MapUtils.build().put("id", id).getMap());
    }

    public List<CommentVo> query1(int id){
        return commentDao.query1(id);
    }

    public int count(Comment comment) {
        return commentDao.count(MapUtils.build().beanToMap(comment));
    }
}
