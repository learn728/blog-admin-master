package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

import java.util.Date;

@Data
public class Collect extends Entity {

    private Integer id;         //ID

    private Integer userId;         //用户id

    private Integer articleId;    //文章id

}
