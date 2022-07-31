package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

@Data
public class Tag extends Entity {

    private Integer id;     //标签ID

    private String tagName; //标签名称
}
