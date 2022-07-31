package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.ArticleAttachment;
import com.yanzhen.mapper.ArticleAttachmentDao;
import com.yanzhen.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleAttachmentService {

    @Autowired
    private ArticleAttachmentDao articleAttachmentDao;

    public int create(ArticleAttachment articleAttachment) {
        return articleAttachmentDao.create(articleAttachment);
    }

    public int delete(Integer id) {
        return articleAttachmentDao.delete(MapUtils.build().put("id", id).getMap());
    }

    public int update(ArticleAttachment articleAttachment) {
        return articleAttachmentDao.update(MapUtils.build().put("id", articleAttachment.getId()).beanToMapForUpdate(articleAttachment));
    }

    public PageInfo<ArticleAttachment> query(ArticleAttachment articleAttachment) {
        if (articleAttachment != null && articleAttachment.getPage() != null) {
            PageHelper.startPage(articleAttachment.getPage(), articleAttachment.getLimit());
        }
        List<ArticleAttachment> list = articleAttachmentDao.query(MapUtils.build().beanToMap(articleAttachment));
        PageInfo<ArticleAttachment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public ArticleAttachment detail(Integer id) {
        return articleAttachmentDao.detail(MapUtils.build().put("id", id).getMap());
    }

    public int count(ArticleAttachment articleAttachment) {
        return articleAttachmentDao.count(MapUtils.build().beanToMap(articleAttachment));
    }
}
