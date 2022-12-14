package com.yanzhen.front;

import com.yanzhen.entity.Channel;
import com.yanzhen.service.ChannelService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/f/channel")
public class FrontChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping("/get")
    public Result get(Integer id) {
        if(id == null){
            System.out.println("master 添加一次");
            return Result.fail();

        }
        Channel detail = channelService.detail(id);
        return Result.success(detail);
    }

    @GetMapping("/queryByPos")
    public Result getChannelByPos(String pos) {
        if (StringUtils.isEmpty(pos)) {
            return Result.fail();
        }
        List<Channel> channels = channelService.getChannelByPos(pos.toUpperCase());
        List<Map<String, Object>> mapList = new ArrayList<>();

        for (Channel channel : channels) {
            //首先获取顶级栏目（递归）
            if (channel.getParentId() == 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", channel.getId());
                map.put("name", channel.getName());
                if (channel.getSingle() != null && "Y".equals(channel.getSingle())) {
                    map.put("single", true);
                }

                List<Map<String, Object>> children = new ArrayList<>();
                for (Channel entity : channels) {
                    if (entity.getParentId() == channel.getId()) {
                        Map<String, Object> subMap = new HashMap<>();
                        subMap.put("id", entity.getId());
                        subMap.put("name", entity.getName());
                        if (entity.getSingle() != null && "Y".equals(entity.getSingle())) {
                            subMap.put("single", true);
                        }
                        children.add(subMap);
                    }
                }
                if (children.size() > 0) {
                    map.put("children", children);
                }
                mapList.add(map);
            }
        }
        return Result.success(mapList);
    }


}
