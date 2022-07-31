package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Article extends Entity {//继承分页属性

    public static Integer status_0 = 0;
    public static Integer status_1 = 1;

    private Integer id;             //文章ID

    private Integer channelId;      //栏目ID

    private String title;           //文章标题

    private String titleImg;        //标题图

    private String summary;         //摘要

    private String author;          //作者

    private String url;             //外链URL

    private String content;         //文章内容

    private Integer status;         //状态 （0待发布 | 1已发布）

    private Integer commentStatus;  //评论状态 （0/NULL允许评论 | 1不允许评论）

    private Integer rotation;       //轮播 （0/NULL非轮播 | 1轮播）

    private Integer top;            //置顶 （0/NULL不置顶 | 1 置顶）

    private Integer orderBy;        //排序

    private Date createDate;        //创建时间

    private Integer createUser;     //创建人

    private Date updateDate;        //修改时间

    private List<Map<String,Object>> attachmentList;
    private List<Integer> selectTagList;
}