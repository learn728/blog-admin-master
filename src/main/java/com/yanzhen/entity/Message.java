package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

import java.util.Date;

@Data
public class Message extends Entity {
    private int id;         //留言id

    private String name;      //留言人

    private String email;       //邮箱


    private Date createDate;    //创建时间

    private String content;     //留言内容


}