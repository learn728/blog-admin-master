package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

@Data
public class ArticleTag extends Entity {

    private Integer id;         //文章标签ID

    private Integer articleId;  //文章ID

    private Integer tagId;      //标签ID
}