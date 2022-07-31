package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Tag;
import com.yanzhen.mapper.TagDao;
import com.yanzhen.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagDao tagDao;

    public int create(Tag tag) {
        return tagDao.create(tag);
    }

    public int delete(Integer id) {
        return tagDao.delete(MapUtils.build().put("id", id).getMap());
    }

    public int update(Tag tag) {
        return tagDao.update(MapUtils.build().put("id", tag.getId()).beanToMapForUpdate(tag));
    }

    public PageInfo<Tag> query(Tag tag) {
        if (tag != null && tag.getPage() != null) {
            PageHelper.startPage(tag.getPage(), tag.getLimit());
        }
        List<Tag> list = tagDao.query(MapUtils.build().beanToMap(tag));
        PageInfo<Tag> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public List<Tag> all() {
        return tagDao.query(null);
    }

    public Tag detail(Integer id) {
        return tagDao.detail(MapUtils.build().put("id", id).getMap());
    }

    public int count(Tag tag) {
        return tagDao.count(MapUtils.build().beanToMap(tag));
    }
}
