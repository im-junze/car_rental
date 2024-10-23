package com.coder.auto_rental.controller;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTPayload;
import com.coder.auto_rental.entity.Permission;
import com.coder.auto_rental.entity.User;
import com.coder.auto_rental.security.CustomAuthenticationException;
import com.coder.auto_rental.service.IUserService;
import com.coder.auto_rental.utils.JwtUtils;
import com.coder.auto_rental.utils.RedisUtils;
import com.coder.auto_rental.utils.Result;
import com.coder.auto_rental.utils.RouteTreeUtils;
import com.coder.auto_rental.vo.RouteVo;
import com.coder.auto_rental.vo.TokenVo;
import com.coder.auto_rental.vo.UserInfoVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auto_rental/auth")
public class AuthController {
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private IUserService userService;

    @PostMapping("/refreshToken")
    public Result<TokenVo> refreshToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            token = request.getParameter("token");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = (String) JwtUtils.parseJWT(token).getClaim("username");
        String newToken = null;
        if (StrUtil.equals(username, userDetails.getUsername())) {
            Map<String, Object> map = new HashMap<>() {
                {
                    put("username", userDetails.getUsername());
                }
            };
            newToken = JwtUtils.createToken(map);
        } else {
            throw new CustomAuthenticationException("token数据异常");
        }
//   获取本次国企时间，并延长
        NumberWithFormat format = (NumberWithFormat) JwtUtils.parseJWT(newToken).getClaim(JWTPayload.EXPIRES_AT);
        DateTime dateTime = (DateTime) format.convert(DateTime.class, format);
        long expireTime = dateTime.getTime();
        TokenVo tokenVo = new TokenVo();
        tokenVo.setExpireTime(expireTime);
        tokenVo.setToken(newToken);
//        清楚原有token
        redisUtils.del("token:" + token);
        long nowTime = DateTime.now().getTime();
        redisUtils.set("token:" + newToken, newToken, (expireTime - nowTime) / 1000);
        return Result.success(tokenVo).setMessage("刷新token成功");
    }

    //    获取用户信息  反馈给前端vo中的
    @GetMapping("/getInfo")
    public Result getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Result.fail().setMessage("认证信息为空");
        }
        User user = (User) authentication.getPrincipal();

//       List<String>list = userService.selectRoleName(user.getId());
//        Object[] array = list.toArray();
        List<Permission> permissionList = user.getPermissionList();
        Object[] array = permissionList.stream().
                filter(Objects::nonNull).
                map(Permission::getPermissionCode).toArray();


        UserInfoVo userInfoVo = new UserInfoVo(user.getId(), user.getUsername(),
                user.getAvatar(), user.getNickname(), array);
        return Result.success(userInfoVo).setMessage("获取成功");

    }

    //获取菜单
    @GetMapping("/menuList")
    public Result getMenuList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Result.fail().setMessage("认证信息为空");
        }
        User user = (User) authentication.getPrincipal();
        List<Permission> permissionList = user.getPermissionList();
//        将type为2 的移除

        for (int i = 0; i < permissionList.size(); i++) {
            Permission permission = permissionList.get(i);
            if (permission == null) {
                permissionList.remove(i);
                i--;
            }
        }

        permissionList.removeIf(permission -> Objects.equals(permission.getPermissionType(), 2));
        List<RouteVo> routeVos = RouteTreeUtils.buildRouteTree(permissionList, 0);
        return Result.success(routeVos).setMessage("获取菜单成功");


    }

    @PostMapping("logout")
    public Result getLogout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            token = request.getParameter("token");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            redisUtils.del("token:" + token);
            SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
            securityContextLogoutHandler.logout(request, response, authentication);
            return Result.success().setMessage("退出成功");
        }
        return Result.fail().setMessage("退出失败");

    }


}
