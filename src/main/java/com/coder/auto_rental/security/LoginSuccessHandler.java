package com.coder.auto_rental.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.coder.auto_rental.entity.User;
import com.coder.auto_rental.utils.JwtUtils;
import com.coder.auto_rental.utils.RedisUtils;
import com.coder.auto_rental.utils.ResultCode;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
   @Resource
   private RedisUtils redisUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        设置响应内容是json
        response.setContentType("application/json;charset=utf-8");
        User user = (User) authentication.getPrincipal();
//        生成token处理
        Map<String,Object>map = new HashMap<>(){
            {
                put("username",user.getUsername());
//                put("userid",user.getId());
            }
        };
        String token = JwtUtils.createToken(map);
//        解析token过期时间，构建认证结果对象

        NumberWithFormat claim = (NumberWithFormat) JwtUtils.parseJWT(token).getClaim(JWTPayload.EXPIRES_AT);
        Long time = Convert.toDate(claim).getTime();
        AuthenticationResult authenticationResult
                = new AuthenticationResult(user.getId(), ResultCode.SUCCESS,token,time);

//        将认证结果转换未字符串json字符串
        String result = JSON.toJSONString(authenticationResult,SerializerFeature.DisableCircularReferenceDetect);
// 向客户端发送认证结果
        //        获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
//        将token存入redis并设置国企时间
        String tokenKey = "token:"+token;
        long nowTime = DateTime.now().getTime();
        redisUtils.set(tokenKey,token,(time-nowTime)/1000);
    }
}
