package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

import java.util.Date;

@Data
public class Channel extends Entity {

    private Integer id;             //栏目ID

    private String name;            //栏目名称

    private Integer parentId;       //上级栏目

    private String channelImg;      //栏目图片

    private String summary;         //摘要

    private String single;          //是否单页 （Y单页|非单页）

    private String url;             //外链URL （链接到其他栏目）

    private String seoTitle;        //seo标题 （SEO搜索引擎优化，是一种利用搜索引擎的搜索规则来提高目前网站在有关搜索引擎内的排名的方式）

    private String seoKeyword;      //seo关键字

    private String seoDescription;  //seo描述

    private String content;         //正文

    private Integer orderBy;        //排序

    private Date createDate;        //创建时间

    private Integer createUser;     //创建人

    private String deletedFlag;     //删除标记 （D删除）

    private String pos;             //位置标记 （ABCDEFG）
}