package com.coder.auto_rental.security;

import cn.hutool.core.util.StrUtil;
import com.coder.auto_rental.utils.JwtUtils;
import com.coder.auto_rental.utils.RedisUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class VerifyTokenFilter extends OncePerRequestFilter {
    @Value("${request.login-url}")
    private String loginUrl;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private LoginFailHandle loginFailHandle;
    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if (!StrUtil.equals(url, loginUrl)) {
//            校验以下token
            try {
                validateToken(request);
            } catch (AuthenticationException e) {
                loginFailHandle.onAuthenticationFailure(request, response, e);
            }
        }
        doFilter(request, response, filterChain);
    }

    private void validateToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            token = request.getParameter("token");
        }
        if (StrUtil.isEmpty(token)) {
            throw new CustomAuthenticationException("token为空");
        }
//        如果token存在，与Redis中比较
        String tokenKey = "token:" + token;
        String s = redisUtils.get(tokenKey);
        if (StrUtil.isEmpty(s)) {
            throw new CustomAuthenticationException("token过期");
        }
        if (!StrUtil.equals(s, token)) {
            throw new CustomAuthenticationException("token错误");
        }
        String username = JwtUtils.parseJWT(token)
                .getClaim("username").toString();
        if (StrUtil.isEmpty(username)) {
            throw new CustomAuthenticationException("token解析失败");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new CustomAuthenticationException("用户不存在");
        }
//        创建并设置认证信息
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request));
//      将认证信息设置到securityContextHolder环境
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }


}
