package com.yanzhen.entity;

import com.yanzhen.utils.Entity;
import lombok.Data;

import java.util.Date;

@Data
public class User extends Entity {

    private Integer id;         //ID

    private String userName;    //用户名

    private String password;    //密码

    private String nickName;    //昵称

    private String avatar;      //头像

    private String email;       //邮箱

    private Date createDate;    //创建时间

    private String status;      //用户状态 （T正常|F禁用|D删除）

    private int type1;        //用户类型
}
