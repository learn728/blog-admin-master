package com.yanzhen.utils;

import lombok.Data;

@Data
public class Entity {
    private Integer page;       //当前页码
    private Integer limit = 8;  //每页显示大小（默认每页显示8条记录）
}
