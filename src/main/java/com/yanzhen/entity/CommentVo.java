package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVo extends Entity {
    private Integer id;         //评论ID

    private Date createDate;    //创建时间

    private String content;     //评论正文

    private Integer userId;      //用户id

    private Integer articleId;  //文章ID

    private Integer parentId;   //父评论

   private String userName;

   private String title;

    private String nickName;
}