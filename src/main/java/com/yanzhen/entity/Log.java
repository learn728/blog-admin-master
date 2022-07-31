package com.yanzhen.entity;


import com.yanzhen.utils.Entity;
import lombok.Data;

import java.util.Date;

@Data
public class Log extends Entity {
    private int id;
    private String name;
    private Date date;
    private String action; //行为

}
