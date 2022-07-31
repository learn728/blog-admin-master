package com.yanzhen.config;

import com.yanzhen.exception.MyException;
import com.yanzhen.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理全局异常
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    //接收处理对应异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody//将异常提示返回到前端
    public Result handle(RuntimeException exception) {
        exception.printStackTrace();//控制台打印错误信息
//        System.out.println(exception.getMessage());

        return Result.fail(exception.getMessage());
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result handle(MyException exception) {
        return Result.fail(exception.getMessage());
    }


}
