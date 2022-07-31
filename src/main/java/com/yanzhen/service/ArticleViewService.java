package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.ArticleView;
import com.yanzhen.mapper.ArticleViewDao;
import com.yanzhen.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleViewService {

    @Autowired
    private ArticleViewDao articleViewDao;

    public int create(ArticleView articleView) {
        return articleViewDao.create(articleView);
    }

    public int delete(Integer id) {
        return articleViewDao.delete(MapUtils.build().put("id", id).getMap());
    }

    public int update(ArticleView articleView) {
        return articleViewDao.update(MapUtils.build().put("id", articleView.getId()).beanToMapForUpdate(articleView));
    }

    public PageInfo<ArticleView> query(ArticleView articleView) {
        if (articleView != null && articleView.getPage() != null) {
            PageHelper.startPage(articleView.getPage(), articleView.getLimit());
        }
        List<ArticleView> list = articleViewDao.query(MapUtils.build().beanToMap(articleView));
        PageInfo<ArticleView> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public ArticleView detail(Integer id) {
        return articleViewDao.detail(MapUtils.build().put("id", id).getMap());
    }

    public int count(ArticleView articleView) {
        return articleViewDao.count(MapUtils.build().beanToMap(articleView));
    }
}
