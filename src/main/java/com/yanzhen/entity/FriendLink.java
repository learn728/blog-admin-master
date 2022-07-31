package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

@Data
public class FriendLink extends Entity {

    private Integer id;     //友情链接ID

    private String url;     //链接URL

    private String title;   //链接标题

    private String path;    //链接图片

    private String target;  //打开方式
}