package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Channel;
import com.yanzhen.mapper.ChannelDao;
import com.yanzhen.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    private ChannelDao channelDao;

    public int create(Channel channel) {
        if(channel.getParentId() == null) {
            channel.setParentId(0);
        }
        return channelDao.create(channel);
    }

    public int delete(Integer id) {
        return channelDao.delete(MapUtils.build().put("id", id).getMap());
    }

    public int update(Channel channel) {
        return channelDao.update(MapUtils.build().put("id", channel.getId()).beanToMapForUpdate(channel));
    }

    public PageInfo<Channel> query(Channel channel) {
        if (channel != null && channel.getPage() != null) {
            PageHelper.startPage(channel.getPage(), channel.getLimit());
        }
        List<Channel> list = channelDao.query(MapUtils.build().beanToMap(channel));
        PageInfo<Channel> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public List<Channel> all(){
        List<Channel> list = channelDao.query(MapUtils.build().getMap());
        return list;
    }

    public Channel detail(Integer id) {
        return channelDao.detail(MapUtils.build().put("id", id).getMap());
    }

    public int count(Channel channel) {
        return channelDao.count(MapUtils.build().beanToMap(channel));
    }

    //根据位置检索栏目信息
    public List<Channel> getChannelByPos(String pos) {
        List<Channel> list = channelDao.query(MapUtils.build().put("pos",pos).getMap());
        return list;
    }
}
