package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Article;
import com.yanzhen.entity.ArticleAttachment;
import com.yanzhen.entity.ArticleTag;
import com.yanzhen.mapper.ArticleAttachmentDao;
import com.yanzhen.mapper.ArticleDao;
import com.yanzhen.mapper.ArticleTagDao;
import com.yanzhen.utils.Entity;
import com.yanzhen.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleAttachmentDao articleAttachmentDao;
    @Autowired
    private ArticleTagDao articleTagDao;

    @Transactional
    public int create(Article article) {
        //先保存主表的数据，得到主表的ID后
        int row = articleDao.create(article);
        //再保存子表的数据
        article.getAttachmentList().forEach(entity -> {
            ArticleAttachment articleAttachment = new ArticleAttachment();
            articleAttachment.setArticleId(article.getId());
            articleAttachment.setDescription(entity.get("name")+"");
            articleAttachment.setUrl(entity.get("url")+"");
            articleAttachmentDao.create(articleAttachment);
        });
        article.getSelectTagList().forEach(x -> {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setId(x);
            articleTagDao.create(articleTag);
        });
        return row;
    }

    //级联删除
    @Transactional
    public int delete(Integer id) {
        int row = articleDao.delete(MapUtils.build().put("id", id).getMap());
        articleAttachmentDao.delete(MapUtils.build().put("articleId",id).getMap());
        articleTagDao.delete(MapUtils.build().put("articleId",id).getMap());
        return row;
    }

    public int update(Article article) {
        int row = articleDao.update(MapUtils.build().put("id", article.getId()).beanToMapForUpdate(article));

        //先删除另外两张表的数据
        articleAttachmentDao.delete(MapUtils.build().put("articleId",article.getId()).getMap());
        articleTagDao.delete(MapUtils.build().put("articleId",article.getId()).getMap());

        //再进行添加数据
        article.getAttachmentList().forEach(entity -> {
            ArticleAttachment articleAttachment = new ArticleAttachment();
            articleAttachment.setArticleId(article.getId());
            articleAttachment.setDescription(entity.get("name")+"");
            articleAttachment.setUrl(entity.get("url")+"");
            articleAttachmentDao.create(articleAttachment);
        });
        article.getSelectTagList().forEach(x -> {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setId(x);
            articleTagDao.create(articleTag);
        });
        return row;
    }

    public PageInfo<Article> query(Article article) {
//        article.setLimit(4);
        if (article != null && article.getPage() != null) {
            //设置分页，             当前页和每页显示条数(由前端传来的数据所得，本程序 limit 后端固定，为8)
            PageHelper.startPage(article.getPage(), article.getLimit());
        }
//        System.out.println(article.getLimit());
        List<Article> list = articleDao.queryRand(MapUtils.build().beanToMap(article));
//        System.out.println(list);
        PageInfo<Article> pageInfo = new PageInfo<>(list);//list经过了分页查询
        return pageInfo;
    }

    public PageInfo<Article> queryTop(Article article) {
//        article.setLimit(4);
        if (article != null && article.getPage() != null) {
            //设置分页，             当前页和每页显示条数(由前端传来的数据所得，本程序 limit 后端固定，为8)
            PageHelper.startPage(article.getPage(), article.getLimit());
        }
//        System.out.println(article.getLimit());
        List<Article> list = articleDao.queryTop(MapUtils.build().beanToMap(article));
//        System.out.println(list);
        PageInfo<Article> pageInfo = new PageInfo<>(list);//list经过了分页查询
        return pageInfo;
    }

    public PageInfo<Article> queryLb(Article article) {
//        article.setLimit(4);
        if (article != null && article.getPage() != null) {
            //设置分页，             当前页和每页显示条数(由前端传来的数据所得，本程序 limit 后端固定，为8)
            PageHelper.startPage(article.getPage(), article.getLimit());
        }
//        System.out.println(article.getLimit());
        List<Article> list = articleDao.queryLb(MapUtils.build().beanToMap(article));
//        System.out.println(list);
        PageInfo<Article> pageInfo = new PageInfo<>(list);//list经过了分页查询
        return pageInfo;
    }


    public PageInfo<Article> query2(Article article) {
//        article.setLimit(4);
        if (article != null && article.getPage() != null) {
            //设置分页，             当前页和每页显示条数(由前端传来的数据所得，本程序 limit 后端固定，为8)
            PageHelper.startPage(article.getPage(), article.getLimit());
        }
//        System.out.println(article.getLimit());
        List<Article> list = articleDao.query(MapUtils.build().beanToMap(article));
//        System.out.println(list);
        PageInfo<Article> pageInfo = new PageInfo<>(list);//list经过了分页查询
        return pageInfo;
    }

//排行部分
    public PageInfo<Article> queryOrder(Article article) {
//        article.setLimit(4);
        if (article != null && article.getPage() != null) {
            //设置分页，             当前页和每页显示条数(由前端传来的数据所得，本程序 limit 后端固定，为8)
            PageHelper.startPage(article.getPage(), article.getLimit());
        }
//        System.out.println(article.getLimit());
        List<Article> list = articleDao.queryOrder(MapUtils.build().beanToMap(article));
//        System.out.println(list);
        PageInfo<Article> pageInfo = new PageInfo<>(list);//list经过了分页查询
        return pageInfo;
    }

    //前台分页查询
    public PageInfo<Article> query1(Article article) {

        if (article != null && article.getPage() != null) {
            //设置分页，             当前页和每页显示条数(由前端传来的数据所得，本程序 limit 后端固定，为8)
            PageHelper.startPage(article.getPage(), 4);
        }
        List<Article> list = articleDao.query1(MapUtils.build().beanToMap(article));
//        System.out.println(list);
        PageInfo<Article> pageInfo = new PageInfo<>(list,4);//list经过了分页查询
        return pageInfo;
    }

    public Article detail(Integer id) {
        Article article = articleDao.detail(MapUtils.build().put("id", id).getMap());
        List<ArticleTag> tagList = articleTagDao.query(MapUtils.build().put("articleId", id).getMap());
        List<ArticleAttachment> attachmentList = articleAttachmentDao.query(MapUtils.build().put("articleId", id).getMap());

        List<Integer> tags = new ArrayList<>();
        tagList.forEach(entity -> {
            tags.add(entity.getTagId());
        });

        List<Map<String,Object>> attachments = new ArrayList<>();
        attachmentList.forEach(entity -> {
            Map<String,Object> map = new HashMap<>();
            map.put("name",entity.getDescription());
            map.put("url",entity.getUrl());
            attachments.add(map);
        });

        article.setSelectTagList(tags);
        article.setAttachmentList(attachments);
        return article;
    }

    public int count(Article article) {
        return articleDao.count(MapUtils.build().beanToMap(article));
    }

    public List<Article> top(Article article,Integer top){
        PageHelper.startPage(0,top);
        List<Article> list = articleDao.query(MapUtils.build().beanToMap(article));
        return list;
    }
}
