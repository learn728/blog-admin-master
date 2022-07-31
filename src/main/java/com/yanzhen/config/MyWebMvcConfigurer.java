package com.yanzhen.config;

import com.yanzhen.interceptor.CollectInterceptor;
import com.yanzhen.interceptor.InterceptorTest;
import com.yanzhen.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {//侵入性比继承WebMvcConfigurationSupport类强

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Bean
    public InterceptorTest interceptorTest(){
        return new InterceptorTest();
    }

    @Bean
    public CollectInterceptor collectInterceptor(){
        return new CollectInterceptor();
    }

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**")         //拦截路径可以理解为getMapping里的路径
                .excludePathPatterns("/login","/static/**","/upload/**","/f/**","/comment/create","/user/create1","/user/create2","user/query1","/comment/query2","/tag/all","/uploadFile");

        //留言和评论拦截
        registry.addInterceptor(interceptorTest())
                .addPathPatterns("/comment/create","/user/create2");

        registry.addInterceptor(collectInterceptor())
                .addPathPatterns("/user/query1");
    }



    //配置静态资源处理器
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:e:/upload/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    //使用CorsFilter过滤器，解决前后端跨域请求问题
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //设置允许跨域请求的域名
        corsConfiguration.addAllowedOrigin("*");
        //设置允许的方法（GET、POST、PUT、DELETE）
        corsConfiguration.addAllowedMethod("*");
        //设置允许任何头部
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addExposedHeader("token");

        //CorsFilter 依赖于 UrlBasedCorsConfigurationSource
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
