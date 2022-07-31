package com.yanzhen.utils;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.Map;

@Data
public class Result {

    private Integer status = 200;    //响应状态码。默认是200
    private String msg = "操作成功";   //响应消息
    private Object data = null;      //响应数据

    private static Integer SUCCESS_CODE = 200;
    private static Integer ERROR_CODE = 500;

    //构造重载
    public Result() {}

    public Result(Object data) {
        this.data = data;
    }

    public Result(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    //成功响应
    public static Result success(Integer status, String msg, Object data) {
        return new Result(status, msg, data);
    }

    public static Result success(String msg, Object data) {
        return new Result(SUCCESS_CODE,msg, data);
    }

    public static Result success(Object data) {
        return new Result(SUCCESS_CODE,"操作成功",data);
    }

    public static Result success() {
        return new Result(SUCCESS_CODE,"操作成功",null);
    }

    //针对分页查询，响应的数据（只需要向前端响应list和total）
    public static Map success(PageInfo pageInfo) {
        return MapUtils.build().put("status",SUCCESS_CODE)
                .put("msg","操作成功")
                .put("list",pageInfo.getList())
                .put("total",pageInfo.getTotal())
                .getMap();
    }

    //失败响应
    public static Result fail(Integer status, String msg) {
        return new Result(status, msg, null);
    }

    public static Result fail(String msg) {
        System.out.println(msg);
        return new Result(ERROR_CODE, msg, null);
    }

    public static Result fail() {
        return new Result(ERROR_CODE, "操作失败");
    }
}