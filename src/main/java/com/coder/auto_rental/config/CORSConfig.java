package com.coder.auto_rental.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {

     @Override
     public void addCorsMappings(CorsRegistry registry) {
//          配置跨域请求的映射
          registry.addMapping("/**")
//                  允许所有来源的跨域请求
                  .allowedOriginPatterns("*")
                  .allowedHeaders("*")
//                  .allowedOrigins("*")
                  .allowedMethods("*")
                  .allowCredentials(true)
                  .maxAge(3600);
     }
}
