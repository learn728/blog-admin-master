package com.yanzhen.interceptor;

import com.yanzhen.entity.User;
import com.yanzhen.exception.MyException;
import com.yanzhen.utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、从请求头中获取token
        String token = request.getHeader("token");
        User user = JWTUtil.getUser1(token);
//        System.out.println(user);
        if(user == null) {
//            System.out.println("原本的拦截器");
            throw new MyException("token已过期或者token不合法");
        }
        //2、token续期（重新生成token）
        String newToken = JWTUtil.sign(user);
        //3、将新的token响应给客户端（token放在请求头中）
        response.setHeader("token",newToken);
        request.setAttribute("user",user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
