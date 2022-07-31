package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.FriendLink;
import com.yanzhen.mapper.FriendLinkDao;
import com.yanzhen.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendLinkService {

    @Autowired
    private FriendLinkDao friendLinkDao;

    public int create(FriendLink friendLink) {
        return friendLinkDao.create(friendLink);
    }

    public int delete(Integer id) {
        return friendLinkDao.delete(MapUtils.build().put("id", id).getMap());
    }

    public int update(FriendLink friendLink) {
        return friendLinkDao.update(MapUtils.build().put("id", friendLink.getId()).beanToMapForUpdate(friendLink));
    }

    public PageInfo<FriendLink> query(FriendLink friendLink) {
        if (friendLink != null && friendLink.getPage() != null) {
            PageHelper.startPage(friendLink.getPage(), friendLink.getLimit());
        }
        List<FriendLink> list = friendLinkDao.query(MapUtils.build().beanToMap(friendLink));
        PageInfo<FriendLink> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public FriendLink detail(Integer id) {
        return friendLinkDao.detail(MapUtils.build().put("id", id).getMap());
    }

    public int count(FriendLink friendLink) {
        return friendLinkDao.count(MapUtils.build().beanToMap(friendLink));
    }
}
