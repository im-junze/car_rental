package com.coder.auto_rental.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.Map;

@Component
public class JwtUtils {
    public static final String SECRET_KEY = "zzz";
    public static final long EXPIRE_TIME = 1000L * 60 * 30;
    public static String createToken(Map<String, Object> payload) {
        DateTime now = DateTime.now();
        DateTime newTime = new DateTime(now.getTime()+EXPIRE_TIME);
        payload.put(JWTPayload.ISSUED_AT,now);//签发时间
        payload.put(JWTPayload.EXPIRES_AT,newTime);//过期时间
        payload.put(JWTPayload.NOT_BEFORE,now);//生效时间
        return JWTUtil.createToken(payload,SECRET_KEY.getBytes());
    }
    public static JWTPayload parseJWT(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        if (!jwt.setKey(SECRET_KEY.getBytes()).verify()){
            throw new RuntimeException("token异常");
        }
        if (!jwt.validate(0)){
            throw new RuntimeException("token无效");
        }
        return  jwt.getPayload();
    }


}
