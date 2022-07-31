package com.yanzhen.utils;

import com.yanzhen.entity.User;
import com.yanzhen.exception.MyException;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTUtil {

    //密钥
    public static String jwt_secret = "yanzhen@blog#&&";
    //过期时间（1天 = 24小时 x 60分钟 x 60秒 x 1000毫秒）
    public static long jwt_expr = 24 * 60 * 60 * 1000;

    //生成token
    public static String sign(User user) {
        //1、创建header（指定签名时要使用的算法）
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //2、创建payload（数据）
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", user.getId());
        payload.put("userName", user.getUserName());
        //生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);
        //生成签发人
        String subject = user.getUserName();
        //创建jwt
        JwtBuilder builder = Jwts.builder()
                .setClaims(payload)   //用户数据
                .setId(UUID.randomUUID().toString()) //随机ID
                .setIssuedAt(date)   //签发人
                .setSubject(subject) //主体
                .signWith(signatureAlgorithm, JWTUtil.jwt_secret); //密钥加密
        //设置过期时间
        Date exprDate = new Date(nowMillis + jwt_expr);
        builder.setExpiration(exprDate);

        //3、返回token
        return builder.compact();
    }

    //验证token
    public static boolean verify(String token) {
        try {
            if(StringUtils.isEmpty(token)) {
                return false;
            }
            Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取数据（用户信息）
    public static User getUser(String token) {
        try {
            if(StringUtils.isEmpty(token)) {
                throw new MyException("token不能为空");
            }
            if(verify(token)) {
                Claims claims = Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(token).getBody();
                User user = new User();
                user.setId(Integer.parseInt(claims.get("id")+""));
                user.setUserName(claims.get("userName")+"");
                return user;
            } else {
                throw new MyException("token已过期或者token不合法");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("token已过期或者token不合法");
        }
    }

    /*普通用户留言评论*/
    public static User getUser1(String token) {
        try {
            if(StringUtils.isEmpty(token)) {
                throw new MyException("未登录");
            }
            if(verify(token)) {
                Claims claims = Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(token).getBody();
                User user = new User();
                user.setId(Integer.parseInt(claims.get("id")+""));
                user.setUserName(claims.get("userName")+"");
                return user;
            } else {
                throw new MyException("没登录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("请先登录吧");
        }
    }


    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setUserName("admin");
        String token = sign(user);
        System.out.println(token);
    }
}
