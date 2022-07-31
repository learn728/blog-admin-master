package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

@Data
public class ArticleAttachment extends Entity {

    private Integer id;         //文章附件ID

    private Integer articleId;  //文章ID

    private String url;         //附件URL

    private String description; //描述

    private String suffix;      //前缀
}