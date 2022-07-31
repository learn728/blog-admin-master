package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleView extends Entity {

    private Integer id;         //文章浏览ID

    private Integer articleId;  //文章ID

    private Date viewDate;      //浏览时间

    private String ip;          //IP
}