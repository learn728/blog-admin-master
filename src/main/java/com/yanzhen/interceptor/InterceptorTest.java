package com.yanzhen.interceptor;

import com.yanzhen.entity.User;
import com.yanzhen.exception.MyException;
import com.yanzhen.utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*评论和留言拦截*/
public class InterceptorTest implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、从请求头中获取token
        String token = request.getHeader("token");//不登录token为空
//        System.out.println(token);
        User user = JWTUtil.getUser1(token);
        if(user == null) {
            throw new MyException("请先注册登录");
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
